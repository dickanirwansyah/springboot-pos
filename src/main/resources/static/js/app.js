$(document).ready(function(){

  $('#btn_submit').on('click', function(e){

        e.preventDefault();
        var firstname = $('#customer_first_name').val();
        var lastname = $('#customer_last_name').val();

        if(!$(".checkbox :checkbox").is(":checked")){
            alert('you must at least select one or more product !');
        }

        else if($.trim(firstname)===""){
          alert('sorry firstname still null please enter the firstname');
        }
        else if($.trim(lastname)===""){
          alert('sorry lastname still null please enter the lastname');
        }

        else{

          var productid = [];
          $(".checkbox :checkbox:checked").each(function(){

            productid.push(parseInt($(this).attr("id")));
          });
          $.ajax({
            type: 'POST',
            url: '/createorder',
            data: {firstname:firstname, lastname:lastname, productid:productid},
            success:function(data){
              alert('data is saved !');
              location.reload();
            }
          });
        }
  });


  //clear orderan
  $('.delete-order').on('click', function(e){

    e.preventDefault();

    if(confirm('are you sure ?')){
        var Id = parseInt($(this).closest("td").attr("id"));

        $.ajax({
          type: 'POST',
          url: '/removeorder',
          data:{Id:Id},
          success:function(data){
            $('.delete-order').closest("td#"+data).parent("tr")
            .fadeOut("slow", function(){
              $('.delete-order').closest("td#"+data).parent("tr").remove();
            });
          }
        });
    }

  });
});
