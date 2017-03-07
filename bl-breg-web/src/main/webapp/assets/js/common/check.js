
// 半角をチェックする
function checkHankaku(str){
    if (str == '') {
    	return true;
    }
    
    var ze;
    var ha;
    var sret = str;
    
    for(j = 0;j < gaHankaku.length; j++){
    		eval( "ha=/" + gaHankaku[j] + "/g");        
            sret = sret.replace(ha, "");     
    	} 
    for(i = 0; i < gaZenkaku.length; i++){
    	eval( "ze=/" + gaZenkaku[i] + "/g");
    	sret = sret.replace(ze, "");
    }
    if (sret == "") {
    	return true;
    }
    return false;
}

function checkDateduration(dateFrom, dateTo) {
	if (dateFrom != "" && dateTo != "") {
		return new Date(dateFrom) <= new Date(dateTo);
	}
	return true;
}

//日付有効性をチェックする
function checkDate(date){
	if (date == null) {
		return true;
	}
	var dateM = date.split("　")[0];
    return new Date(dateM).getDate() == dateM.substring(dateM.length-2);
}
