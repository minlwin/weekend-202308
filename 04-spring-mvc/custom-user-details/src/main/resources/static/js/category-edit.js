document.addEventListener('DOMContentLoaded', () => {
	
	const addNewButton = document.getElementById('addNewButton')
	const closeButton = document.getElementById('closeButton')
	
	const categoryEditDialog = document.getElementById('categoryEditDialog')
	const modalDialog = new bootstrap.Modal(categoryEditDialog)
	
	if(addNewButton && closeButton && categoryEditDialog) {
		
		if(categoryEditDialog.dataset.show == 'true') {
			modalDialog.show()
		}
		
		addNewButton.addEventListener('click', () => {
			document.getElementById('id').value = 0
			document.getElementById('name').value = ''
			document.getElementById('description').value = ''
			document.getElementById('deleted').value = 'false'
			modalDialog.show()
		})
		
		closeButton.addEventListener('click', () => {
			modalDialog.hide()
		})
	}
	
	const editButtons = document.getElementsByClassName('editButton')
	Array.from(editButtons).forEach(btn => btn.addEventListener('click', () => {
		document.getElementById('id').value = btn.dataset.id
		document.getElementById('name').value = btn.dataset.name
		document.getElementById('description').value = btn.dataset.description
		document.getElementById('deleted').value = btn.dataset.deleted
		
		Array.from(document.getElementById('deleted').children).forEach(option => {

			console.log(option.value)
			console.log(option.innerText)
			console.log(btn.dataset.deleted)
			
			if(toString(option.value) == toString(btn.dataset.deleted)) {
				console.log("Found")
				option.setAttribute('selected', 'selected')
			} else {
				option.removeAttribute('selected')
			}
		})
		
		modalDialog.show()
	}))
})