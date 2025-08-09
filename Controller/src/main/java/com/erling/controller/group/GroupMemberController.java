package com.erling.controller.group;

import com.erling.entity.group.GroupMember;
import com.erling.service.group.GroupMemberService;
import com.erling.utils.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/group/member")
public class GroupMemberController {
    GroupMemberService groupMemberService;
    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }
    @PostMapping("/add")
    public ResponseEntity<Result<?>> addGroupMember(
            @RequestPart GroupMember groupMember,
            @RequestPart MultipartFile file

    ) throws IOException {

            return groupMemberService.addGroupMember(groupMember, file.getBytes());
    }
    @PostMapping("/verify/{id}")
    public ResponseEntity<Result<?>> verifyGroupMember(
            @PathVariable int id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return groupMemberService.verifyGroupMemberMysql(id, file.getBytes());
    }

}

