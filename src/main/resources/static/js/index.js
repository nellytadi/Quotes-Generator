var quoteTags =  JSON.parse(decodeHtml(tags));
var resultData = [];
for(var i in quoteTags){
	resultData.push( quoteTags[i].tag);

}

function decodeHtml(text){
	var txt = document.createElement("textarea");
	txt.innerHTML = text;
	return txt.value;
	
}

var quotes = new Bloodhound({
	local: resultData,
	queryTokenizer: Bloodhound.tokenizers.whitespace,
	datumTokenizer: Bloodhound.tokenizers.whitespace
});
quotes.initialize();

$('select').tagsinput({
	 confirmKeys: [13, 44],
	 trimValue: true,
	  typeaheadjs: {
	    name: 'quotes',
	    source: quotes.ttAdapter()
	  },
	
}
);




$(".toggle-form").click(function(){
	$(".form-tag").toggle(200);
});