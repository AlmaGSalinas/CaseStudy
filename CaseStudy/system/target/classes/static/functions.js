//DELETE EMPLOYEE FUNCTION MODAL
function deleteEmp(id) {
    swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this employee data!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url: "/deleteEmployee/" + id,
                    success: function(res) {
                        console.log(res);
                    },
                });
                swal("The information has been deleted!", {
                    icon: "success",
                }).then((ok) => {
                    if (ok) {
                        location.href = "/EmployeeControl";
                    }
                });
            } else {}
        });
}


//DELETE EMPLOYEE FUNCTION MODAL
function deleteComp(id) {
    swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this employee data!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url: "/deleteCompensation/" + id,
                    success: function(res) {
                        console.log(res);
                    },
                });
                swal("The information has been deleted!", {
                    icon: "success",
                }).then((ok) => {
                    if (ok) {
                        location.href = "/EmployeeControl";
                    }
                });
            } else {}
        });
}