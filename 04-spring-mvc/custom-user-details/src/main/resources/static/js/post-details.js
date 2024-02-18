document.addEventListener('DOMContentLoaded', () => {
	
	const imageDisplay = document.getElementById('imageDisplay')
	const imageControls = document.getElementsByClassName('img-control')
	
	if(imageDisplay && imageControls) {
		Array.from(imageControls).forEach(image => image.addEventListener('click', () => {
			imageDisplay.src = image.src
		}))
	}
	
	const showStar = (value, links) => {

		links.forEach(link => {
			end = link.dataset.value > value
						
			Array.from(link.children).forEach(child => {
				child.classList = end ?  ['bi-star'] : ['bi-star-fill']
 			})
		})
	}
	
	const ratingInput = document.getElementById('ratingInput')
	const starLinks = Array.from(document.getElementsByClassName('star-link'))
	
	if(ratingInput && starLinks) {
		starLinks.forEach(link => link.addEventListener('click', () => {
			ratingInput.value = link.dataset.value
			showStar(link.dataset.value, starLinks)
		}))
	}
})