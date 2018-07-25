function exception2() {
  $.ajax({
    type: 'GET',
    url: "/exception2",
    success: function (data) {
      $('#message1').empty().html(data).show();
    },
    error: function (data) {
      alert('error');
    }
  });
  return false;
}