var registry = $(window).adaptTo("foundation-registry");

registry.register("foundation.validation.validator", {
        selector : "[data-validation=geeks-multifield-validation]",
        validate : function(element){
          var el = $(element);
          let max = el.data("max-items");
          let min = el.data("min-items");
          let items = el.children("coral-multifield-item").length;
          let domitems = el.children("coral-multifield-item")
          console.log("{} : {} : {} ",max,min,items);
          if(items>max){
              domitems.last().remove();
              return "you can add only " +max+ " books. But Your Trying to add "+items+ " books";
          }
          if(items<min){
              return "At least You have to add " +min+ " books";
           }
        }
    });

registry.register("foundation.validation.validator",{
       selector : "[data-validation=geeks-text-validation]",
       validate : function(element){
        var el = $(element);
        let pattern = /[0-9a-z]/;
        let value = el.val();
        if(pattern.test(value)){
          return "You Have to give Lower Case Characters Only";
        }
      }
});
