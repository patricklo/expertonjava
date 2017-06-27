package sample.apidoc.swagger.controller;

import com.wordnik.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.apidoc.swagger.model.UamGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lopatrip on 6/23/2017.
 */
@Controller
@RequestMapping(value="/group",produces = {"appliation/json;charset=UTF-8"})
@Api(value="/group",description = "群组的相关操作")
public class GroupController {

    @RequestMapping(value="addGroup",method = RequestMethod.PUT)
    @ApiOperation(notes="addGroup",httpMethod = "POST",value="群组:add a new group")
    @ApiResponses(value={@ApiResponse(code=405,message="invalid inputs")})
    public UamGroup addGroup(@ApiParam(required = true,value = "group data") @RequestBody UamGroup group ){
        return group;
    }

    @RequestMapping(value = "getAccessibleGroups", method=RequestMethod.GET)
    @ApiOperation(notes = "getAccessasfasdfasdf",httpMethod = "GET",value="get group list")
    public List<UamGroup> getAccessibleGroup(){
        UamGroup group1 = new UamGroup();
        group1.setGroupId("1");
        group1.setName("testGroup1");

        UamGroup group2 = new UamGroup();
        group2.setGroupId("2");
        group2.setName("testGroup2");

        List<UamGroup> groupList = new LinkedList<UamGroup>();
        groupList.add(group1);
        groupList.add(group2);

        return groupList;
    }
}
