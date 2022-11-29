<template>
    <div>
        <h1>Editar Cadastro de Flor</h1>
        <form>
            <label for="name">Nome: </label>
            <input type="text" name="name" id="name" v-model="flower.name" placeholder="Nome da flor">
            <label for="scientificName">Nome Científico: </label>
            <input type="text" name="scientificName" id="scientificName" v-model="flower.scientificName"
                placeholder="Nome científico">
            <label for="color">Cor: </label>
            <input type="text" name="color" id="color" v-model="flower.color" placeholder="Cor">
            <label for="family">Família: </label>
            <select v-model="flower.family">
                <option disabled value=""> Selecionar família </option>
                <option v-for="fm in families" :value="fm" :key="fm.id">{{ fm.familyName }}</option>
            </select>
            <button class="btn info" @click="update()"> Atualizar </button>
        </form>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue"
import env from "/env.json"
import { useRoute } from "vue-router";
import axios from "axios";

const apiURL = env.apiURL

const route = useRoute()

const flower = ref({
    id: null,
    name: "",
    scientificName: "",
    color: "",
    family: ""
})

const load = () => {
    let id = route.params.id
    axios.get(apiURL + "/flower/" + id)
        .then((response) => {
            flower.value.id = response.data.id;
            flower.value.name = response.data.name;
            flower.value.scientificName = response.data.scientificName;
            flower.value.color = response.data.color;
            flower.value.familyName = response.data.familyName;
        }).catch((error) => {
            alert("Erro: " + error.response.status)
        })
}

const update = () => {
    axios.put(apiURL + "/flower", flower.value)
        .then((response) => {
            if (response.data != "") {
                alert("Dados atualizados com sucesso!")
                load()
            }
        }).catch((error) => {
            alert("Erro: " + error.response.status)
        })
}

onMounted(() => {
    load();
})
</script>

<style scoped>
/* Style inputs */
input[type=text],
select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

/* Style the submit button */
input[type=submit] {
    width: 100%;
    background-color: #04AA6D;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

/* Add a background color to the submit button on mouse-over */
input[type=submit]:hover {
    background-color: #45a049;
}

.btn {
    border: 2px solid black;
    background-color: dodgerblue;
    color: black;
    padding: 14px 28px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
}

/* Blue */
.info {
    border-color: #2196F3;
    color: white
}

.info:hover {
    border-color: blue;
    background: blue;
    color: white;
}
</style>