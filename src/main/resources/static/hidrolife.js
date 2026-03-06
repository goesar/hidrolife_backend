
console.log("Hidrolife.js cargado ✔️");
const criteriosPorTabla = {
    "Actividades": [
        {valor: "id", texto: "ID"},
        {valor: "actividades", texto: "Actividad"},
        {valor: "usuario", texto: "Usuario"},
        {valor: "fecha", texto: "Fecha"}
    ],
    "Cultivos": [
        {valor: "id", texto: "ID"},
        {valor: "nombre", texto: "Nombre"},
        {valor: "numeroPlantas", texto: "Número de plantas"},
        {valor: "phIdeal", texto: "pH ideal"},
        {valor: "tdsIdeal", texto: "TDS ideal"},
        {valor: "fecha", texto: "Fecha"}
    ],
    "Usuarios": [
        {valor: "id", texto: "ID"},
        {valor: "nombre", texto: "Nombre"},
        {valor: "telefono", texto: "Teléfono"},
        {valor: "email", texto: "Email"}
    ],
    "Lecturas": [
        {valor: "id", texto: "ID"},
        {valor: "ph", texto: "pH"},
        {valor: "tds", texto: "TDS"},
        {valor: "humedad", texto: "Humedad"},
        {valor: "temperatura", texto: "Temperatura"}

    ]
};

function actualizarCriterios() {
    const tablaSelect = document.getElementById("tablaSelect");
    const criteriosSelect = document.getElementById("criterioSelect");
    if (!tablaSelect || !criteriosSelect) return;
    const tabla = tablaSelect.value;
    const opciones = criteriosPorTabla[tabla];

    criteriosSelect.innerHTML = ""; // limpia el select

    opciones.forEach(op => {
        const option = document.createElement("option");
        option.value = op.valor;
        option.textContent = op.texto;
        criteriosSelect.appendChild(option);
    });

}

// Ejecuta esto una vez al cargar por primera vez
document.addEventListener("DOMContentLoaded", actualizarCriterios);

function actualizarTDS() {
    fetch("http://localhost:8080/api/lecturas/ultima")
            .then(res => res.json())
            .then(data => {
                document.getElementById("valorTDS").innerText = data.tds.toFixed(2);
            })
            .catch(err => console.log("Error TDS:", err));
}

function actualizarHumedad() {
    fetch("http://localhost:8080/api/lecturas/ultima")
        .then(res => res.json())
        .then(data => {
            document.getElementById("valorHumedad").innerText = data.humedad.toFixed(1);
        })
        .catch(err => console.log("Error Humedad:", err));
}

function actualizarTemperatura() {
    fetch("http://localhost:8080/api/lecturas/ultima")
        .then(res => res.json())
        .then(data => {
            document.getElementById("valorTemperatura").innerText = data.temperatura.toFixed(2);
        })
        .catch(err => console.log("Error Temperatura:", err));
}


// setInterval(actualizarTemperatura, 2000);
// actualizarTemperatura();
//
// setInterval(actualizarTDS, 2000);
// actualizarTDS();
// setInterval(actualizarHumedad, 2000);
// actualizarHumedad();

//Deshabilitar campo de fechas
document.addEventListener("DOMContentLoaded", () => {
    const tablaSelect = document.getElementById("tablaSelect");
    const bloques = document.querySelectorAll(".bloque-fecha");

    function aplicar() {
        const esUsuarios = tablaSelect.value === "Usuarios";
        bloques.forEach(b => b.classList.toggle("no-aplica", esUsuarios));
    }

    tablaSelect.addEventListener("change", aplicar);
    aplicar();
});


