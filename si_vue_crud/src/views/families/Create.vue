<template>
    <div>
        <h1>Cadastrar Nova Família</h1>
        <form>
         <label for="familyName">Família</label>
         <input type="text" name="familyName" id="familyName" v-model="family.familyName" placeholder="Nome da Família">
         <button class="btn info" @click="save()"> Cadastrar </button>
        </form> 
    </div>
</template>

<script setup>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import env from "/env.json"


    const apiURL = env.apiURL

    const family = reactive({
        familyName: ""
    })

    const save = () => {
        axios.post(apiURL + "/family", family)
        .then((response) => {
            if(response.status === 200){
                family.familyName = ""
                alert("Dados Cadastrados com sucesso!")                
            }
        }).catch( (error) => {
            alert("Erro: " + error.response.status)
        } )
    }

</script>

<style scoped>
/* Style inputs */
input[type=text], select {
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