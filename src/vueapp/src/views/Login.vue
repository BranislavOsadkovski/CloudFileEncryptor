<template>
  <div id="main">
    <div>
      <form action="" method="POST" enctype="">

          <input type="text" name="username" placeholder="Username or email"/>
          <input type="password" name="password" placeholder="Password"/>
          <input type="submit" value="Login"/>
 Dont have an account? <button @click="redirect()">Sign in </button>
      </form>
     
    </div>
  </div>
</template>

<script>

export default {
methods:{
    redirect(){
        let a = document.createElement("a");
        a.href="http://localhost:8081/shop/signup";
        document.body.appendChild(a);
        a.click();
    }
}
};
</script>

<style scoped></style>
