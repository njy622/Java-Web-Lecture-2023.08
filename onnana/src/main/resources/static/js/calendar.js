/**
 * calendar.js
 * 		calendar.jsp 에서 사용하는 자바스크립트 코드
 */

 var schedClicked = false;
 
function cellClick(date) {
	if (schedClicked)
		schedClicked = false;
	else {
		date = date + '';		// number type을 문자열로 변환
		const dateForm = date.substring(0,4)+'-'+date.substring(4,6)+'-'+date.substring(6,8);
		let t = new Date();
		$('#startDate').val(dateForm);
		$('#addModal').modal('show');
	}
}

function schedClick(sid) {
	schedClicked = true;
	$.ajax({
		type: 'GET',
		url: '/onnana/schedule/detail/' + sid,
		success: function(jsonSched) {
			let sched = JSON.parse(jsonSched);
			$('#sid2').val(sched.sid);
			$('#title2').val(sched.title);
			if (sched.isImportant == 1)
				$('#importance2').prop('checked', true);
			$('#startDate2').val(sched.startTime.substring(0,10));
			$('#place2').val(sched.place);
			$('#memo2').val(sched.memo);
			$('#updateModal').modal('show');
		}
	});
}

function deleteSchedule() {
	let sid = $('#sid2').val();
	const answer = confirm('정말로 삭제하시겠습니까?');
	if (answer) {
		location.href = '/onnana/schedule/delete/' + sid;
	}
}

function addAnniversary() {
	$('#addAnnivModal').modal('show');
}

function addAnnivList() {
	$('#addAnnivListModal').modal('show');
}


