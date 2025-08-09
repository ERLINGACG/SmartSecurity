package com.erling.service.group;

import com.erling.dao.group.GroupMapper;
import com.erling.entity.group.Group;
import com.erling.utils.log.Logger;
import com.erling.utils.result.Result;
import com.erling.utils.result.ResultEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    GroupMapper  groupMapper;
    public GroupService(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public ResponseEntity<Result<?>> addGroup(Group group) {
        try{
            if(groupMapper.getGroup(group.getGroupName(),group.getGroupEmail()) != null){
                return ResponseEntity.
                        badRequest().
                        body(new Result<>(ResultEnum.GROUP_EXIST,null));

            }
            return ResponseEntity.
                    ok().
                    body(new Result<>
                            (ResultEnum.GROUP_ADD_SUCCESS,
                                    groupMapper.insertGroup(group)
                            )
                    );
        }catch(Exception e){
            Logger.getLogger(GroupService.class).error("添加分组失败",e);
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_ADD_FAIL,e.getMessage()));
        }
    }
    public ResponseEntity<Result<?>> deleteGroup(int groupId,String groupEmail) {
        try{
            if(groupMapper.deleteGroup(groupId,groupEmail)){
                return ResponseEntity.
                        ok().
                        body(new Result<>(ResultEnum.GROUP_DELETE_SUCCESS,null));
            }
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_NOT_EXIST,null));
        }catch(Exception e){
            Logger.getLogger(GroupService.class).error("删除分组失败",e);
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_DELETE_FAIL,e.getMessage()));
        }
    }
    public ResponseEntity<Result<?>> updateGroup(Group group) {
        try{
            if(groupMapper.getGroupById(group.getGid()) != null){
                return ResponseEntity.
                        ok().
                        body(new Result<>(ResultEnum.GROUP_UPDATE_SUCCESS,
                                groupMapper.updateGroup(group)
                        ));
            }
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_NOT_EXIST,null));
        }catch(Exception e){
            Logger.getLogger(GroupService.class).error("更新分组失败",e);
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_UPDATE_FAIL,e.getMessage()));
        }
    }

    public ResponseEntity<Result<?>> getGroup(String groupName,String groupEmail) {
        try{
            Group group = groupMapper.getGroup(groupName,groupEmail);
            if(group != null){
                return ResponseEntity.
                        ok().
                        body(new Result<>(ResultEnum.GROUP_SELECT_SUCCESS,group));
            }
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_NOT_EXIST,null));
        }catch(Exception e){
            Logger.getLogger(GroupService.class).error("获取分组失败",e);
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_SELECT_SUCCESS,e.getMessage()));
        }
    }
    public ResponseEntity<Result<?>> getGroups(String groupEmail) {
        try{
            return ResponseEntity.
                    ok().
                    body(new Result<>(ResultEnum.GROUP_SELECT_SUCCESS,groupMapper.getGroups(groupEmail)));
        }catch(Exception e){
            Logger.getLogger(GroupService.class).error("获取所有分组失败",e);
            return ResponseEntity.
                    badRequest().
                    body(new Result<>(ResultEnum.GROUP_SELECT_SUCCESS,e.getMessage()));
        }
    }
}
