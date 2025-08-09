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
@Service
public class GroupMemberService {

    GroupMemberMapper  groupMemberMapper;

    FacialRecognitionE facialRecognitionE;
    Pointer faceRec;
    Load RF = new Load(FacialRecognitionE.class);


    public GroupMemberService(GroupMemberMapper groupMemberMapper) {
        this.groupMemberMapper = groupMemberMapper;
        this.facialRecognitionE = RF.loading();
        FaceNew faceNew=new FaceNew();
        faceNew.predictor_path="lib/x64/debug/shape_predictor_68_face_landmarks.dat";
        faceNew.recognition_Path="lib/x64/debug/dlib_face_recognition_resnet_model_v1.dat";
        faceRec=facialRecognitionE.createFacialRecognition(faceNew);

    }
    public ResponseEntity<Result<?>> addGroupMember(GroupMember groupMember,byte[] imageInput) {
         try{
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
               if(distance<0.5){
                   return ResponseEntity.ok(
                           new Result<>(
                                   ResultEnum.MEMBER_VERIFY_SUCCESS,
                                   distance
                           )
                   );
               }
             }
             return ResponseEntity.ok(
                     new Result<>(
                             ResultEnum.MEMBER_VERIFY_DISTANCE_HIGH,
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

}
