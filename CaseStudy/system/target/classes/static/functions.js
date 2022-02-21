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
                    url: "/delete/" + id,
                    success: function(res) {
                        console.log(res);
                    },
                });
                swal("The information has been deleted!", {
                    icon: "success",
                }).then((ok) => {
                    if (ok) {
                        location.href = "/employees";
                    }
                });
            } else {}
        });
}

//VALIDACION DE EMPLEADOS DUPLICADOS

/*
function modifyEmp(id) {
    swal({
            title: "Are you sure to modify the information?",
            icon: "question",
            buttons: true,
            dangerMode: true,
        })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url: "/update/" + id,
                    success: function(res) {
                        console.log(res);
                    },
                });
                swal("The information has been update", {
                    icon: "success",
                }).then((ok) => {
                    if (ok) {
                        location.href = "/employees";
                    }
                });
            } else {}
        });

}
*/