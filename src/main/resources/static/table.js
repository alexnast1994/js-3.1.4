$(document).ready(getAllUsers());

// USER TABLE
function getAllUsers() {
    $("#table").empty();
    $.ajax({
        type: 'GET',
        url: '/main',
        timeout: 3000,
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                $("#table").append($('<tr>').append(
                        $('<td>').append($('<span>')).text(user.id),
                        $('<td>').append($('<span>')).text(user.firstName),
                        $('<td>').append($('<span>')).text(user.lastName),
                        $('<td>').append($('<span>')).text(user.email),
                        $('<td>').append($('<span>')).text(user.role),
                        $('<td>').append($('<button>').text("Edit").attr({
                            "type": "button",
                            "class": "btn btn-primary edit",
                            "data-toggle": "modal",
                            "data-target": "#myModal",

                        })
                            .data("user", user)),
                        $('<td>').append($('<button>').text("Delete").attr({
                            "type": "button",
                            "class": "btn btn-danger delete",
                            "data-toggle": "modal",
                            "data-target": "#myModalDelete",
                        })
                            .data("user", user))
                    )
                );
            });
        }
    });
}

// EDIT USER MODAL
$(document).on("click", ".edit", function () {
    let user = $(this).data("user");

    $('#idInput').val(user.id).hide();
    $('#usernameInput').val(user.firstName);
    $('#lastnameInput').val(user.lastName);
    $('#emailInput').val(user.email);
    $('#passwordInput').val(null);
    $('#roleInput').val(user.role);

});

$(document).on("click", ".editUser", function () {
    let formData = $(".myForm").serializeArray();
    $.ajax({
        type: 'PUT',
        url: '/updateUser',
        data: formData,
        timeout: 3000,
        success: function () {
            getAllUsers();
        }
    });
});
// DELETE USER MODAL
$(document).on("click", ".delete", function () {
    let user = $(this).data('user');
    $('#delId').val(user.id).hide();
    $(document).on("click", ".deleteUser", function () {
        $.ajax({
            type: 'DELETE',
            url: '/deleteUser',
            data: {id: $('#delId').val()},
            timeout: 500,
            success: function () {
                getAllUsers();
            }
        });
    });
})
// ADD USER FORM
$('.addUser').click(function () {
    $('#usersTable').trigger('click');
    let user = $(".formAddUser").serializeArray();

    $('#id').val(user.id).val(0).hide();
    $('#firstName').val('');
    $('#lastName').val('');
    $('#email').val('');
    $('#password').val('');
    $('#role').val('');

    $.ajax({
        type: 'POST',
        url: '/saveUser',
        data: user,
        timeout: 100,
        success: function () {

            $('.formAddUser')[0].reset();
            getAllUsers()
        }
    })
});
// USER FORM
$(document).ready(getUser());
function getUser() {
    $("#userTable").empty();
    $.ajax({
        type: 'GET',
        url: '/getUser',
        timeout: 3000,
        error: function() {
            $('#forUser').hide();
        },
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                if(user.role === "ROLE_USER") {
                    $('#v-pills-profile-tab').trigger('click');
                    $('#v-pills-profile').trigger('click');
                    $('#v-pills-home-tab').hide();
                }
                $("#userTable").append($('<tr>').append(
                        $('<td>').append($('<span>')).text(user.id),
                        $('<td>').append($('<span>')).text(user.firstName),
                        $('<td>').append($('<span>')).text(user.lastName),
                        $('<td>').append($('<span>')).text(user.email),
                        $('<td>').append($('<span>')).text(user.role),
                    )
                );
            });
        }
    });
}