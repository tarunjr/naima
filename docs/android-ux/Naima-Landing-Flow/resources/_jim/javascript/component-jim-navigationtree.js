/*!
 * Copyright 2013 Justinmind. All rights reserved.
 */

(function(window, undefined) {
  var $navigationTree = jQuery("#sidepanel #navigationtree"),
      $canvasName = jQuery("#infoContent").find("#canvasname"),
	  $requirements = jQuery("#sidepanel .requirement .requirement-node"),
	  $reqComponents = jQuery("#sidepanel .requirement .requirement-component");
  $navigationTree
    .click(function(event) {
      var $firer = jQuery(event.target || event.srcElement), $node = $firer.closest("li");
      if($node.length) {
        event.stopPropagation();
        if(($firer.hasClass("icon") && !$node.hasClass("leaf")) || $node.hasClass("folder")) {
          $node.toggleClass("closed").toggleClass("open");
        } else if (!$node.hasClass("folder")) {
          jimMain.navigate($node.children("a").attr("href"), {"transition": "none"}, 2);
        }
        return false;
      }
    })
    .bind("load", function(event, target) {
      var name;
      $navigationTree.find(".current").removeClass("current").end().find("a").each(function(i, link) {
        if(link.attributes.href.value === target) {
          link.className += " current";
		  $(link).closest("li").addClass("current");
          return false;
        }
      });
      
      name = lookUpName(target);
      $canvasName.text("/ "+name).attr("title", name);
    });
  
  function showElement(element, done) {
	element.show();
	var height = element[0].getBoundingClientRect().height;
	element.css("height", 0);
	element.animate({height:height}, {
		done: function() {
		  element.css("height", "");
		  done();
		}
	});
  }
  
  function hideElement(element, done) {
	element.animate({height:0},
		{done: function() {
		  element.css("height","");
		  element.hide();
		  done();
		}}
	);
  }
  
  $(".sidepanelTab").bind("click", function (event) {
  	var parent = $($(this).parent());
  	if (!$(this).hasClass("active") && !parent.hasClass("animation")) {
  		parent.addClass("animation");
  		var active = $(this).parent().children(".active");
  		
  		active.removeClass("active");
  		$(this).addClass("active");
  		
  		var screens = $("#sidepanel .screens.section");
  		var scenarios = $("#sidepanel .scenario.section");
  		var requirements = $("#sidepanel .requirement.section");
  		
  		screens.stop();
  		scenarios.stop();
  		requirements.stop();
  		
  		var newActive;
  		if (this.id == "scenarioTab") newActive = scenarios;
  		else if (this.id == "screenTab") newActive = screens;
  		else newActive = requirements;
  		
  		var oldId = active.attr("id");
  		var oldActive;
  		if (oldId == "scenarioTab") oldActive = scenarios;
  		else if (oldId == "screenTab") oldActive = screens;
  		else oldActive = requirements;
  		
  		//FIREFOX FIX
  		oldActive.attr("openHeight", oldActive[0].getBoundingClientRect().height);
  		oldActive.animate({height:0},{duration: 400});
  		
  		newActive.show();
  		newActive.css("height","");
		var h = newActive[0].getBoundingClientRect().height;
		if (h == 0) h = newActive.attr("openHeight"); // firefox
  		newActive.css("height",0);
  		newActive.animate({height:h}, {duration: 400});

  		setTimeout(function () {parent.removeClass("animation")}, 450);
  	}
  });
 
  $requirements.click(function(event) {
	var obj = $(this);
	obj.closest(".section").css("height", "");
	
	if (!obj.hasClass("animation")) {
	  var target = obj.parent().children(".requirement-content");
	  obj.addClass("animation");
	  var removeClass = function () {obj.removeClass("animation")};
	  
	  if (!obj.hasClass("active")) {
	    var oldActive = $($(obj.closest("#navigationtree")).find("span.active"));
	    if (oldActive.length > 0) {
	      oldActive.removeClass("active");
		  obj.addClass("active");
		  hideElement(oldActive.parent().children(".requirement-content"), removeClass);
          showElement(target, removeClass);
	    }
	    else {
	      obj.addClass("active");
		  showElement(target, removeClass);
        }
      }
      else {
        obj.removeClass("active");
        hideElement(target, removeClass);
      }
	}
  });
  
  $reqComponents.click(function(event) {
  	var obj = $(this);
  	var canvas = obj.attr("screen");
  	var component = obj.attr("componentID");
	var prefix = canvas.substring(0,2);
  	
  	if (!jimUtil.hasCanvas(canvas)) {
  	  var folder;
  	  
  	  if (prefix == "s-") folder = "screens/";
  	  if (prefix == "t-") folder = "templates/";
  	  if (prefix == "m-") {
  		folder = "masters/";
  		if (canvas.substring(2) != component) component = canvas.substring(2,10) + "-" + component;
  	  }
  	  else if (prefix == "sc") {
  		if ($(document.body).hasClass("noScenarios")) return;
  		prefix += "-";
  		folder = "scenarios/";
  	  }

  	  jimMain.navigate(folder + canvas.substring(prefix.length));
  	  $("#simulation").attr("toHighlight", prefix + component);
  	} else {
  	  var masterclass = "";
  	  var m = "";

  	  // master
  	  if (canvas.substring(0,1) == "m") {
  		if (canvas.substring(2) != component) m = canvas.substring(2,10) + "-";
  		if ($("#" + prefix + m + component).closest(".masterinstance").length > 0) masterclass = ".masterinstance ";
  	  } 
  	  
  	  // scenario
  	  if (prefix == "sc") prefix += "-";
  	  
      var c = $(masterclass + "#" + prefix + m + component);
      jimHighlight.highlightElement(c);
    }
  });
 
})(window);