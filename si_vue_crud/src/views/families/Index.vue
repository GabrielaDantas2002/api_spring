<template>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <div>
    <h1>Familias Cadastradas</h1>
    <hr />
    <table>
      <tr>
        <th>Nome</th>
        <th>Editar</th>
        <th>Excluir</th>
      </tr>
      <tr v-for="fm in families">
        <td>{{ fm.familyName }}</td>
        <td>
          <router-link :to="{ name: 'fam-edit', params: { id: fm.id } }"
            ><button class="btn fa fa-edit"></button></router-link>
        </td>
        <td><button class="btn fa fa-trash" @click="destroy(fm.id)"></button></td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import env from "/env.json";
import { onMounted, ref } from "@vue/runtime-core";
import axios from "axios";

const apiURL = env.apiURL;

const families = ref([]);

const findAll = () => {
  axios
    .get(apiURL + "/family")
    .then((response) => {
      families.value = response.data;
    })
    .catch((error) => {
      alert("Erro: " + error.response.status);
    });
};

const destroy = (id) => {
  if (confirm("Deseja excluir a família selecionada?")) {
    axios
      .delete(apiURL + "/family/" + id)
      .then((response) => {
        if (response.status === 200 && response.data == "") {
          alert("Dados Excluídos com sucesso!");
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
  console.log("data: " + families.value);
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
