/**
 * 
 */
package com.aran.tech.managementArea.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aran.tech.managementArea.domain.PermissionAction;
import com.aran.tech.managementArea.services.PermissionActionService;

/**
 * @author oawon
 *
 */
@RestController
@RequestMapping("/api/permissionAction")
@CrossOrigin
public class PermissionActionConntroller {
	
	@Autowired
	private PermissionActionService permissionActionService ;

	@GetMapping("/all")
	public Iterable<PermissionAction> getAllPermissionActions(Principal principal) {
		List<PermissionAction> permissionActions = permissionActionService.findAllPermissionActions(principal.getName()) ;
		return permissionActions ;
	}
	
	@GetMapping("/{permissionActionId}")
	public ResponseEntity<?> getPermissionActionById(@PathVariable String permissionActionId, Principal principal) {

		PermissionAction permissionAction = permissionActionService.findPermissionActionByIdentifier(permissionActionId, principal.getName());

		return new ResponseEntity<PermissionAction>(permissionAction, HttpStatus.OK);
	}
}
