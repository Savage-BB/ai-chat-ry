<template>
    <div id="app">
      <h1>ChatGPT Chat</h1>
      <div class="chat-container">
        <div v-for="message in messages" :key="message.id" class="message" :class="{ 'user-message': message.sender === 'user', 'gpt-message': message.sender === 'gpt' }">
          {{ message.text }}
        </div>
      </div>
      <input v-model="userInput" @keyup.enter="sendMessage" placeholder="Type a message and hit Enter" />
    </div>
   </template>
    
   <script>
   export default {
    data() {
      return {
        messages: [],
        userInput: ''
      };
    },
    methods: {
      sendMessage() {
        if (this.userInput.trim() === '') {
          return;
        }
        const userMessage = {
          id: this.messages.length,
          text: this.userInput,
          sender: 'user'
        };
        this.messages.push(userMessage);
    
        // 以下是模拟的GPT响应，你需要替换为API调用
        setTimeout(() => {
          const gptMessage = {
            id: this.messages.length,
            text: 'This is a response from GPT.',
            sender: 'gpt'
          };
          this.messages.push(gptMessage);
        }, 500);
    
        // 清空输入框
        this.userInput = '';
      }
    }
   };
   </script>
    
   <style>
   #app {
    max-width: 600px;
    margin: 0 auto;
    font-family: Arial, sans-serif;
   }
    
   .chat-container {
    border: 1px solid #ccc;
    height: 300px;
    overflow-y: auto;
    padding: 10px;
    margin-bottom: 10px;
   }
    
   .message {
    padding: 5px;
    margin: 5px 0;
    background-color: #f3f3f3;
    border-radius: 5px;
   }
    
   .user-message {
    background-color: #cde4f5;
    text-align: right;
   }
    
   .gpt-message {
    background-color: #e8f4ff;
    text-align: left;
   }
    
   input {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
   }
   </style>