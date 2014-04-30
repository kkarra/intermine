(jQuery(function() { // run when the page has loaded.
	if(!intermine) 
		return; 
	//delete intermine.results.formatters.Publication;
	//delete intermine.results.formatsets.genomic["Publication.title"];
	intermine.setOptions({
		"Publication.title": function(pub){
		// formatter code here
		return pub.get("title").toUpperCase();
	}
	},
			"intermine.results.formatsets.genomic");
}));