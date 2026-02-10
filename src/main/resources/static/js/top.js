$(function(){
	$('.delete-button').on('click', function(){
		let result = window.confirm('削除してもよろしいですか？');
		if(result == false){
			event.preventDefault();
		}
	});
});

$(function(){
	$('.update-status-button').on('click', function(){
		let result = window.confirm('変更してもよろしいですか？');
		if(result == false){
			event.preventDefault();
		}
	});
});
