fetch("/Servlets/api")
    .then(res => res.json())
    .then(data => {

        if (data.length > 0) {
            data.map(producto => {
                const { nombre, marca, precio } = producto;
                create(tbody, nombre, marca, precio);
            })
            foot.innerHTML = data.length;
        }
        else {
            create(empty)
            foot.innerHTML = 0;
        }
    })