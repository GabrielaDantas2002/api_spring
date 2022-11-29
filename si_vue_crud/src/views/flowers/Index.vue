<template>
    <div>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <h1>Flores Cadastradas</h1>
        <hr>
        <table>
            <tr>
                <th>Nome</th>
                <th>Nome Científico</th>
                <th>Cor</th>
                <th>Família</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            <tr v-for="f in flowers">
                <td>{{ f.name }}</td>
                <td>{{ f.scientificName }}</td>
                <td>{{ f.color }}</td>
                <td v-for="fm in families" :value="fm" :key="fm.id">{{fm.familyName}}</td>
                <td><router-link :to="{ name: 'flo-edit', params: {id: f.id} }" ><button class="btn fa fa-edit"></button></router-link></td>
                <td><button class="btn fa fa-trash" @click="destroy(f.id)"></button></td>
            </tr>
        </table>
    </div>
</template>

<script setup>

import env from "/env.json";
import { onMounted, ref } from "@vue/runtime-core";
import axios from "axios";

const apiURL = env.apiURL;

const flowers = ref([]);

const findAll = () => {
    axios.get(apiURL + "/flower")
    .then((response) => {
        console.log("Response: " + JSON.stringify(response.data))
        flowers.value = response.data
    })
    .catch((error) => {
        alert("Error: " + error.response.data)
    })
    
    axios.get(apiURL + "/family")
    .then((response) => {
        console.log("Response: " + JSON.stringify(response.data))
        families.value = response.data
    })
    .catch((error) => {
        alert("Error: " + error.response.data)
    })
}

const destroy = (id) => {
    if (confirm("Deseja excluir a flor selecionada?" )) {
    axios
        .delete(apiURL + "/flower" + id)
        .then((response) => {
            if (response.status === 200 && response.data == "") {
                alert("Dados excluídos com sucesso!");
                findAll();
            }
        })
        .catch((error) => {
            alert("Erro: " + error.response.status);
        });
    }
};

onMounted(() => {
    findAll();
    console.log("data: " + flowers.value);
});

</script>

<style scoped>

tr:nth-child(even) {
  background-color: #dddddd;
}

table {
  width: 100%;
}

.btn {
  /* background-color: DodgerBlue; Blue background */
  border: none; /* Remove borders */
  color: black; /* White text */
  padding: 12px 16px; /* Some padding */
  font-size: 16px; /* Set a font size */
  cursor: pointer; /* Mouse pointer on hover */
}

</style>