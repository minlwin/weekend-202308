document.addEventListener('DOMContentLoaded', () => {

	const home = new URL(document.location)
	const baseUrl = home.origin

	// Load Category Chart
	fetch(`${baseUrl}/admin/api/categories`).then(resp => {
		if(resp.ok) {
			return resp.json()
		}
	}).then(result => {
		const array = Array.from(result)
		const names = array.map(a => a.name)
		const values = array.map(a => a.count)
		loadCategories(names, values)
	})
	
	
	// Load Top Users
	fetch(`${baseUrl}/admin/api/top-users`).then(resp => {
		if(resp.ok) {
			return resp.json()
		}
	}).then(result => loadToUser(result))
	
	
	// Load Post Data
	loadPostData()	

})

const loadToUser = users => {
	const topTenMembers = document.getElementById('topTenMembers')
	Array.from(users).forEach(user => {
		let item = document.createElement('li')
		item.classList.add('list-group-item')
		item.innerText = user.name
		topTenMembers.appendChild(item)
	})
}

const loadCategories = (names, values) => {
	const categoryChart = document.getElementById('categoryChart')
	
	if(categoryChart) {
		
		new Chart(categoryChart, {
			type: 'pie',
			data: {
				labels: names,
				datasets: [{
					data: values,
					hoverOffset: 4
				}]
			},
			options: {
				plugins: {
					legend: {
						position: 'bottom'
					}	
				}
			}
		})	
	}	
}

const loadPostData = () => {
	
	const searchType = document.getElementById('searchType')
	const searchYear = document.getElementById('searchYear')
	const searchMonth = document.getElementById('searchMonth')
	
	function loadChart() {
		// load data according to search type
		const type = searchType.value
		if(type == 'year') {
			loadForYear(searchYear.value)
		} else {
			loadForMonth(searchYear.value, searchMonth.value)
		}
	}
	
	searchType.addEventListener('change', () => {
		// hide month select
		const type = searchType.value

		if(type == 'year') {
			searchMonth.classList.add('d-none')
		} else {
			searchMonth.classList.remove('d-none')
			initMonth(searchMonth)
		}
		
		loadChart()
	})
	
	searchYear.addEventListener('change', () => {
		// reset month to JAN
		initMonth(searchMonth)
		
		// load data according to search type
		loadChart()
	})
	
	searchMonth.addEventListener('change', () => {
		// load data according to search type
		console.log("Change Month")
		loadChart()
	})
	
	loadChart()
}

const initMonth = select => {
	let array = Array.from(select.children)
	array[0].selected = true
}

const loadForMonth = (year, month) => {

	const home = new URL(document.location)
	const baseUrl = home.origin
	
	var queryString = `${encodeURIComponent('year')}=${encodeURIComponent(year)}&${encodeURIComponent('month')}=${encodeURIComponent(month)}`
	
	// Load Category Chart
	fetch(`${baseUrl}/admin/api/post/month?${queryString}`).then(resp => {
		if(resp.ok) {
			return resp.json()
		}
	}).then(result => {
		const array = Array.from(result)
		const names = array.map(a => a.name)
		const values = array.map(a => a.count)
		loadChartData(names, values)
	})
	
}	

const loadForYear = (year) => {
	const home = new URL(document.location)
	const baseUrl = home.origin

	var queryString = `${encodeURIComponent('year')}=${encodeURIComponent(year)}`

	// Load Category Chart
	fetch(`${baseUrl}/admin/api/post/year?${queryString}`).then(resp => {
		if(resp.ok) {
			return resp.json()
		}
	}).then(result => {
		const array = Array.from(result)
		const names = array.map(a => a.name)
		const values = array.map(a => a.count)
		loadChartData(names, values)
	})	
}	

let barChart = undefined

const loadChartData = (names, values) => {
	
	let chartData = {
		labels: names,
		datasets: [{
			label: "Post Data",
			data: values,
			hoverOffset: 4
		}]			
	}
	
	if(barChart) {
		barChart.data = chartData
		barChart.update()
	} else {
		const postChart = document.getElementById('postChart')
		
		if(postChart) {
			barChart = new Chart(postChart, {
				type: 'bar',
				data: chartData,
				options: {
					plugins: {
						legend: {
							position: 'bottom'
						}	
					}
				}
			})	
		}		
	}
	
}
