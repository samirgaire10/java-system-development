function toggleMenu() {
  const menu = document.querySelector(".menu-links");
  const icon = document.querySelector(".hamburger-icon");
  menu.classList.toggle("open");
  icon.classList.toggle("open");
}



const toggle = document.getElementById('toggleDark');
const body = document.querySelector('body');

toggle.addEventListener('click', function(){
    this.classList.toggle('bi-moon');
    if(this.classList.toggle('bi-brightness-high-fill')){
        body.style.background = 'skyblue';
        body.style.color = 'black';
        body.style.transition = '2s';
    }else{
        body.style.background = '#135874';
        body.style.color = 'white';
        body.style.transition = '2s';
    }
});

