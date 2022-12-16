const button = document.querySelector('button');
const form = document.forms.addForm;

const toAdd = (e) => {

    e.preventDefault();

    const producto = {
        nombre: form.nombre.value.trim(),
        marca: form.marca.value.trim(),
        precio: form.precio.value.trim()
    }

    if (producto.nombre && producto.marca && producto.precio) {

        fetch("/Servlets/api", {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(producto)
        })
            .then(res => res.json())
            .then(({ error }) => {
                !error
                    ? location.href = '/Servlets'
                    : alert('Hubo problemas a la hora de guardar el producto. Intenta otra vez.');
            })
    }
    else alert('Debes completar todos los datos');
}

button.addEventListener('click', toAdd);