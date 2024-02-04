document.addEventListener('DOMContentLoaded', () => {
	
	const searchForm = document.getElementById('searchForm')
	const pageParam = document.getElementById('pageParam')
	const sizeParam = document.getElementById('sizeParam')
	
	const pageSizeSelect = document.getElementById('pageSizeSelect')
	
	if(searchForm && pageSizeSelect && pageParam && sizeParam) {
		pageSizeSelect.addEventListener('change', () => {
			pageParam.value = 0
			sizeParam.value = pageSizeSelect.value 
			searchForm.submit()
		})
	}
	
	const pagination = document.getElementById('pagination')
	const buttons = pagination.getElementsByTagName('button')
	Array.from(buttons).forEach(button => button.addEventListener('click', () => {
		pageParam.value = button.dataset.value
		searchForm.submit()
	})) 
	
})