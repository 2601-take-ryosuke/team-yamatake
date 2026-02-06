$(function(){
	$('.delete-button').on('click', function(){
		let result = window.confirm('削除してもよろしいですか？');
		if(result == false){
			event.preventDefault();
		}
	});
});
