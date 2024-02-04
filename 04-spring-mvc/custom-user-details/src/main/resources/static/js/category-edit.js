document.addEventListener('DOMContentLoaded', () => {
	
	const addNewButton = document.getElementById('addNewButton')
	const closeButton = document.getElementById('closeButton')
	const categoryEditDialog = document.getElementById('categoryEditDialog')
	
	if(addNewButton && closeButton && categoryEditDialog) {
		const modalDialog = new bootstrap.Modal(categoryEditDialog)
		
		addNewButton.addEventListener('click', () => {
			modalDialog.show()
		})
		
		closeButton.addEventListener('click', () => {
			modalDialog.hide()
		})
	}
})