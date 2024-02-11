document.addEventListener('DOMContentLoaded', () => {
	const photoUploadBtn = document.getElementById('photoUploadBtn')
	const photoUploadForm = document.getElementById('photoUploadForm')
	const photoUploadInput = document.getElementById('photoUploadInput')
	
	if(photoUploadBtn && photoUploadForm && photoUploadInput) {
		photoUploadBtn.addEventListener('click', () => photoUploadInput.click())
		photoUploadInput.addEventListener('change', () => photoUploadForm.submit())		
	}
})