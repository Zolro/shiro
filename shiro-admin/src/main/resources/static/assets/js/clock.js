    //判断时间是否为个位数，如果时间为个位数就在时间之前补上一个“0”
	function check(val) {
		if (val < 10) {
			return ("0" + val);
		} 
		else {
			return (val);
		}
	}
	function displayTime() {
		//获取div元素
		var time_box=document.getElementById("time_box");
		var date_box=document.getElementById("date_box");
		//获取系统当前的年、月、日、小时、分钟、毫秒
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var hour = date.getHours();
		var minutes = date.getMinutes();
		var second = date.getSeconds();
		var timestr =check(hour) + ":" + check(minutes) + ":" + check(second);
		var datestr= year + "年" + month + "月" + day + "日  " ;
		//将系统时间设置到div元素中
		time_box.innerHTML = timestr;
		date_box.innerHTML = datestr;
	}
	//每隔1秒调用一次displayTime函数
	function start(){
        window.setInterval("displayTime()",1000)//单位是毫秒
}