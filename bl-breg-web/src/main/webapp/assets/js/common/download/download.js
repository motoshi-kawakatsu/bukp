jQuery.download = function(url, method, filedir, filename){
    jQuery('<form action="'+url+'" method="'+(method||'post')+'">' +
                '<input type="text" name="filedir" value="'+filedir+'"/>' +
                '<input type="text" name="filename" value="'+filename+'"/>' +
            '</form>')
    .appendTo('body').submit().remove();
};
