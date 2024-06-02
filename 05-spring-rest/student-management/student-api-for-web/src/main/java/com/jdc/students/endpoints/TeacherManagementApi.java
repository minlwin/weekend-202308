package com.jdc.students.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({
	"admin/teachers",
	"office/teachers"
})
public class TeacherManagementApi {

}
