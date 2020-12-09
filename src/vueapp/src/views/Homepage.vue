<template id="main">
  <div id="app">
    <h3>Vue App</h3>
    <h5>Label Spring message : { {{ this.message }} }</h5>
    <button @click="getMsg()">Load data after</button>{ {{ this.msg }} }<br />

    <br />
    <a href="http://localhost:8081/homepage"
      >Go to Thymeleaf Spring dependency</a
    ><br /><br />
    <div>
      <form
        action="http://localhost:8081/shop/upload"
        method="POST"
        enctype="multipart/form-data"
      >
        <input type="file" name="file" value="Choose file" />
        <input type="submit" value="Submit" />
      </form>
      <br />

      <br />
      <div id="btm">
        <div class="if" v-if="show">
          <button @click="renderFiles()">Show Files</button>
        </div>
        <div class="if" v-else>
          <ul id="example-1">
            <li v-for="item in filenames" :key="item.id">
              <b>{{ item.name }}  </b>
              <button class="baton" @click="downloadFile(item.name)">
                Download
              </button>
            </li>
          </ul>
          <button @click="renderFiles()">Hide Files</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Homepage",
  components: {},
  data() {
    return {
      show: true,
      msg: "",
      message: "",
      filenames: [], 
      fileurl:"http://localhost:8081/shop/download/",
    };
  },
  mounted() {
    this.getMessage();
    this.getFileNames(); 
  },
  methods: {
    renderFiles() {
      if (this.show) {
        this.show = false;
      } else {
        this.show = true;
      }
    }, 
    getFileNames() {
      axios.get("http://localhost:8081/shop/files").then((response) => {
        this.filenames = response.data; 
      });
    },
    getMessage() {  
      axios
        .get("http://localhost:8081/shop/message", { withCredentials: true })
        .then((response) => {
          this.message = response.data.message;
        });
    },
    getMsg() {
      axios.get("http://localhost:8081/shop/go").then((response) => {
        this.msg = response.data;
      });
    },
    downloadFile(file) {
      axios.get("http://localhost:8081/shop/download/"+file,{ withCredentials: true })
      .then((response) => {
        response.data;
        var fileLink = document.createElement("a");
        fileLink.href ="http://localhost:8081/shop/download/"+file; 
        fileLink.download="download"
        document.body.appendChild(fileLink);
        fileLink.click();
      });
    },
  },
};
</script>

<style>
#red {
  background-color: red;
  height: 100px;
}
#blue {
  float: right;
  height: 100px;
  width: 100px;
  background-color: blue;
}
li {
  
  height: 40px;
}
body {
  background-color: rgb(178, 178, 230);
}
#main {
  background-color: rgb(62, 90, 90);
}
#btm {
  justify-content: center;
  justify-items: center;
  align-items: center;
  background-color: rgb(195, 255, 255);
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background-color: rgb(255, 255, 255);
  height: 100%;
  width: 100%;
}
.arr {
  width: 100%;
  height: 30px;
}
.if {
  align-items: center;
  width: 50%;
}
.baton{
  float:right;
}
</style>
