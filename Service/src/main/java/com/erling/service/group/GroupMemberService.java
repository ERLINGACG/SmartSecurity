package com.erling.service.group;

import com.erling.dao.group.GroupMemberMapper;
import com.erling.entity.group.GroupMember;
import com.erling.lib.dlib.struct.param.FaceNew;
import com.erling.lib.instance.Load;
import com.erling.service.group.dlib.FacialRecognitionE;
import com.erling.utils.log.Logger;
import com.erling.utils.result.Result;
import com.erling.utils.result.ResultEnum;
import com.sun.jna.Pointer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.erling.lib.dlib.struct.data.Output;

import java.time.LocalDateTime;

@Service
public class GroupMemberService {

    GroupMemberMapper  groupMemberMapper;

    FacialRecognitionE facialRecognitionE;
    Pointer faceRec;
    Load RF = new Load(FacialRecognitionE.class);


    public GroupMemberService(GroupMemberMapper groupMemberMapper) {
        this.groupMemberMapper = groupMemberMapper;
        try{
            this.facialRecognitionE = RF.loading();
            FaceNew faceNew=new FaceNew();
            faceNew.predictor_path="lib/x64/debug/shape_predictor_68_face_landmarks.dat";
            faceNew.recognition_Path="lib/x64/debug/dlib_face_recognition_resnet_model_v1.dat";
            faceRec=facialRecognitionE.createFacialRecognition(faceNew);
        }catch(Exception e){
            Logger.getLogger(GroupMemberService.class).error("加载模型失败",e);
        }
    }
    public ResponseEntity<Result<?>> addGroupMember(GroupMember groupMember,byte[] imageInput) {
         try{
             groupMember.setUpdateTime(LocalDateTime.now());
             Output output=new Output();
             facialRecognitionE.getDetection(faceRec,imageInput,imageInput.length,output);
             groupMember.setMemberFeature(output.getBuffer());
             return ResponseEntity.ok(
                     new Result<>(
                             ResultEnum.MEMBER_ADD_SUCCESS,
                             groupMemberMapper.insertGroupMember(groupMember)
                     )
             );
         }catch(Exception e){
             Logger.getLogger(GroupMemberService.class).error("添加成员失败",e);
             return ResponseEntity.ok(
                     new Result<>(
                             ResultEnum.MEMBER_ADD_FAIL,
                             null
                     )
             );
         }
    }
    public ResponseEntity<Result<?>> verifyGroupMemberMysql(int gid, byte[] imageInput) {
         try{
             double result=0;
             Output output=new Output();
             facialRecognitionE.getDetection(faceRec,imageInput,imageInput.length,output);
             for(GroupMember groupMember:groupMemberMapper.selectGroupMembers(gid)){
               double distance =   facialRecognitionE.getDistance(
                          faceRec,
                          output.getBuffer(),
                          output.getBuffer().length,
                          groupMember.getMemberFeature(),
                          groupMember.getMemberFeature().length);
               result=distance;
               System.out.println("distance:"+distance);
               System.out.println("result:"+result);
               if(distance<0.5){
                   return ResponseEntity.ok(
                           new Result<>(
                                   ResultEnum.MEMBER_VERIFY_SUCCESS,
                                   distance
                           )
                   );
               }else{
                   return ResponseEntity.ok(
                           new Result<>(
                                   ResultEnum.MEMBER_VERIFY_DISTANCE_HIGH,
                                   result
                           )
                   );
               }
             }
             return ResponseEntity.ok(
                     new Result<>(
                             ResultEnum.MEMBER_VERIFY_GROUP_ISNULL,
                             result
                     )
             );

         }catch(Exception e){
             Logger.getLogger(GroupMemberService.class).error("验证成员失败",e);
             return ResponseEntity.ok(
                     new Result<>(
                             ResultEnum.MEMBER_VERIFY_FAIL,
                             null
                     )
             );
         }
    }

    public ResponseEntity<Result<?>> getGroupMembers(int gid){
        try{
            return ResponseEntity.ok(
                    new Result<>(200,
                            "获取成功",
                            groupMemberMapper.selectGroupMembers(gid)
                    )
            );
        }catch(Exception e){
            Logger.getLogger(GroupMemberService.class).error("获取成员失败",e);
            return ResponseEntity.ok(
                    new Result<>(
                            ResultEnum.INTERNAL_SERVER_ERROR,
                            e.getMessage()
                    )
            );
        }
    }
    public ResponseEntity<Result<?>> updateGroupMember(GroupMember groupMember){
        try{
            groupMember.setUpdateTime(LocalDateTime.now());
            return ResponseEntity.ok(
                    new Result<>(200,
                            "更新成功",
                            groupMemberMapper.updateGroupMember(groupMember)
                    )
            );
        }catch(Exception e){
            Logger.getLogger(GroupMemberService.class).error("更新成员失败",e);
            return ResponseEntity.ok(
                    new Result<>(
                            ResultEnum.INTERNAL_SERVER_ERROR,
                            e.getMessage()
                    )
            );
        }
    }
    public ResponseEntity<Result<?>> deleteGroupMember(int gid,int mid){
        try{
            return ResponseEntity.ok(
                    new Result<>(200,
                            "删除成功",
                            groupMemberMapper.deleteGroupMember(gid,mid)
                    )
            );
            }
        catch(Exception e){
            Logger.getLogger(GroupMemberService.class).error("删除成员失败",e);
            return ResponseEntity.ok(
                    new Result<>(
                            ResultEnum.INTERNAL_SERVER_ERROR,
                            e.getMessage()
                    )
            );
        }
    }

}
