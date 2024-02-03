document.addEventListener('DOMContentLoaded', () => {
	const changeMemberStatusBtn = document.getElementById('changeMemberStatusBtn')
	const changeMemberStatusForm = document.getElementById('changeMemberStatusForm')
	
	if(changeMemberStatusBtn && changeMemberStatusForm) {
		changeMemberStatusBtn.addEventListener('click', () => {
			changeMemberStatusForm.submit()
		})
	}
	
	const changeMemberRoleBtn = document.getElementById('changeMemberRoleBtn')
	const changeMemberRoleForm = document.getElementById('changeMemberRoleForm')
	
	if(changeMemberRoleBtn && changeMemberRoleForm) {
		changeMemberRoleBtn.addEventListener('click', () => {
			changeMemberRoleForm.submit()
		})
	}
})