<template>
 <div>
  <div class="container">
    <img src="../images/img1.jpg" style="width:100%;">
        <div class="text-block">
         <h1>Catálogo de Flores</h1>
        </div>
</div>
 </div>
</template>

<script setup>

</script>

<style scoped>

/* Container holding the image and the text */
.container {
  position: relative;
}

/* Bottom right text */
.text-block {
  position: absolute;
  bottom: 250px;
  right: 250px;
  background-color: black;
  color: white;
  padding-left: 250px;
  padding-right: 250px;
}

</style>