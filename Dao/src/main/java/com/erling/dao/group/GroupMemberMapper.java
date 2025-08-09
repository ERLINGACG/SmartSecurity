package com.erling.dao.group;

import com.erling.entity.group.GroupMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupMemberMapper {

    @Insert("INSERT INTO smartsecuritydb.groupmember (" +
            "groupId,memberEmail," +
            "memberName,memberIdentity,memberGender,memberDescription,memberFeature) " +
            "VALUES (#{groupId}, " +
            "#{memberEmail}," +
            "#{memberName},#{memberIdentity},#{memberGender},#{memberDescription},#{memberFeature})")
    boolean insertGroupMember(GroupMember groupMember);


    @Delete("DELETE FROM smartsecuritydb.groupmember WHERE groupId=#{groupId} AND memberEmail=#{memberEmail}")
    boolean deleteGroupMember(int groupId,String memberEmail);


    @Update("UPDATE smartsecuritydb.groupmember SET " +
            "memberName=#{memberName}, memberIdentity=#{memberIdentity}, " +
            "memberFeature=#{memberFeature}, memberDescription=#{memberDescription} " +
            "WHERE groupId=#{groupId} AND memberEmail=#{memberEmail}")
    boolean updateGroupMember(GroupMember groupMember);

    @Select("SELECT * FROM smartsecuritydb.groupmember where groupmember.groupId=#{gid}")
    List<GroupMember> selectGroupMembers(int gid);

    @Select("SELECT * FROM smartsecuritydb.groupmember where groupmember.groupId=#{gid} AND groupmember.memberEmail=#{email}")
    GroupMember selectGroupMember(int gid,String email);



}