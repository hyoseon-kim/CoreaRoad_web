<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="no-js" lang="ko">
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Foundation | Welcome</title>
  <link href="http://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css" rel="stylesheet">
  <link rel="stylesheet" href="css/foundation.min.css">
  <!--<link href='http://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css' rel='stylesheet' type='text/css'>-->
  <link rel="stylesheet" href="css/slide.css">
  <link rel="stylesheet" href="css/main.css">
</head>

<!--상단 메뉴바-->
<div class="top-bar">
  <div class="_top_menu">
    <div class="row" style="max-width: 50rem">
      <div class="small-9 small-centered large-uncentered columns">
        <ul class="menu">
          <li><a href="#color" class="menu_title" style="color:#E9634B;">VISION</a></li>
          <li><a href="#member" class="menu_title" style="color:#EB9039">MATE</a></li>
          <li><img src="img/logo.png"></li>
          <li><a href="#" class="menu_title" style="color:#EAD402">PROCESS</a></li>
          <li><a href="#contact" class="menu_title" style="color:#52BCDB">CONTACT</a></li>
        </ul>
      </div>

      <!-- temp login area -->
      <div>
        <button type="button" class="button _login_btn" data-toggle="example-dropdown">Login</button>
      </div>

      <!-- temp login area -->
    </div>
  </div>
</div>

<!-- temp login layer -->
<div class="dropdown-pane" id="example-dropdown" data-dropdown data-auto-focus="true">
  <form action="/login.do" method="post">
    <div class="row">
      <div class="medium-6 columns">
        <label>email
          <input type="text" placeholder="Kirk, James T."name="email" class="_email">
        </label>
      </div>
      <div class="medium-6 columns">
        <label>password
          <input type="password" class="_password" name="password">
        </label>
      </div>
    </div>
    <c:if test="${applicationScope}">
      <button class="button _login" type="submit">Login</button>
       <a href="#" class="button">Sign Up</a>
    </c:if>
  </form>
</div>
<!-- temp login layer -->

<c:choose>
  <c:when test="${applicationScope}">
  ${sessionScope.get("loginUser")}
  </c:when>
  <c:otherwise>
    <div class="dropdown-pane" id="example-dropdown" data-dropdown data-auto-focus="true">
      <form action="/login.do" method="post">
        <div class="row">
          <div class="medium-6 columns">
            <label>email
              <input type="text" placeholder="Kirk, James T."name="email" class="_email">
            </label>
          </div>
          <div class="medium-6 columns">
            <label>password
              <input type="password" class="_password" name="password">
            </label>
          </div>
        </div>
        <button class="button _login" type="submit">Login</button>
        <a href="#" class="button">Sign Up</a>
      </form>
    </div>
  </c:otherwise>
</c:choose>


<!--컨텐츠 영역-->
<div class="content">
  <div style="position: relative">
    <div class="orbit" role="region" aria-label="Favorite Space Pictures" data-orbit>
      <ul class="orbit-container">
        <button class="orbit-previous" aria-label="previous"><span class="show-for-sr">Previous Slide</span>&#9664;</button>
        <button class="orbit-next" aria-label="next"><span class="show-for-sr">Next Slide</span>&#9654;</button>
        <li class="orbit-slide is-active" style="top: 85px;">
          <img src="img/1.jpg" width="100%">
        </li>
        <li class="orbit-slide">
          <img src="img/3.jpg" width="100%">
        </li>
        <li class="orbit-slide">
          <img src="img/4.jpg" width="100%">
        </li>
        <li class="orbit-slide">
          <img src="img/5.jpg" width="100%">
        </li>
      </ul>
    </div>
    <div style="position: absolute;top: 70%;left: 40%;">
      <a class="large button" href="#" style="background-color: rgba(255,255,255,0.4);font-size: 2.25em;border:2px solid;">
        R U READY ?
      </a>
    </div>
  </div>
</div>


<!--color-->
<div class="tabs-content" data-tabs-content="example-tabs" id="color">
  <div class="tabs-panel is-active" id="panel1" aria-hidden="false">
    <h1>PLAY COLOR <small>Our goal</small></h1>
    <div class="contents_all">
      <div class="aboutplay" style="width:100%">
        <a href="#" class="cbtn" onclick="openLayer('poster1',{top:0, left:0});return false;">
          <img src="img/contents/contents_play.jpg" style="padding:0.2rem;"></a>
      </div>
      <div class="abouteat">
        <a href="#" class="cbtn" onclick="openLayer('poster2',{top:0, left:0});return false;">
          <img src="img/contents/contents_eat.png" style="cursor:pointer;padding:0.2rem;"></a>
      </div>
      <div class="abouteat">
        <a href="#" class="cbtn" onclick="openLayer('poster3',{top:0, left:0});return false;">
          <img src="img/contents/contents_exprerience.png" style="cursor:pointer;padding:0.2rem;"></a>
      </div>
    </div>
  </div>
</div>



<!-- pop up layer -->
<div id="poster1" class="layer-popup">
  <!-- inside popup-->
  <div id="play-color" class="reveal-modal remove-whitespace">
    <div class="row">
      <!-- left side div -->
      <div class="large-6 columns auth-plain" style="width:70%;">
        <div class="signup-panel left-solid">
          <img src="img/play.jpg">
        </div>
      </div>
      <!-- right side div -->
      <div class="large-6 columns auth-plain" style="width:30%;">
        <div class="signup-panel newusers">
          <p>Playing with 'SAT GAT MAN' is a crazy thing. U know?<P>
        </div>
      </div>
    </div>
    <a href="#" class="close">x</a>
  </div>
</div>

<div id="poster2" class="layer-popup">
  <!-- inside popup-->
  <div id="eat-color" class="reveal-modal remove-whitespace">
    <div class="row">
      <!-- left side div -->
      <div class="large-6 columns auth-plain" style="width:70%;">
        <div class="signup-panel left-solid">
          <img src="img/eat.jpg">
        </div>
      </div>
      <!-- right side div -->
      <div class="large-6 columns auth-plain" style="width:30%;">
        <div class="signup-panel newusers">
          <p>Eating with 'SAT GAT MAN' is a crazy thing. U know?<P>
        </div>
      </div>
    </div>
    <a href="#" class="close">x</a>
  </div>
</div>

<div id="poster3" class="layer-popup">
  <!-- inside popup-->
  <div id="eat-color" class="reveal-modal remove-whitespace">
    <div class="row">
      <!-- left side div -->
      <div class="large-6 columns auth-plain" style="width:70%;">
        <div class="signup-panel left-solid">
          <img src="img/heal.jpg">
        </div>
      </div>
      <!-- right side div -->
      <div class="large-6 columns auth-plain" style="width:30%;">
        <div class="signup-panel newusers">
          <p>Experience with 'SAT GAT MAN' is a crazy thing. U know?<P>
        </div>
      </div>
    </div>
    <a href="#" class="close">x</a>
  </div>
</div>

<!-- popup background-black -->
<div class="popup_background">
</div>


<!--Member-->
<div class="row" id="member">
  <h1>Member <small>Who are we?</small></h1>
  <div class="medium-4 columns">
    <div class="profile-card">
      <img src="img/profile/suji.jpg" alt="Inky">
      <div class="profile-info">
        <h4 class="subheader">Inky</h4>
        <p>Inky or inking can substitute for any word having to do with cool or awesome.</p>
        <p><strong>Used in a scentence -</strong> Yo man, Foundation for Emails is so inky. Coding emails doesn't suck anymore!</p>
        <p><a href="http://foundation.zurb.com/forum">Forum</a> | <a href="http://zurb.com/ink/">Website</a></p>
        <ul class="inline-list">
          <li><a href="https://www.facebook.com/ZURB"><i class="fi-social-facebook"></i></a></li>
          <li><a href="https://twitter.com/zurbink"><i class="fi-social-twitter"></i></a></li>
          <li><a href="https://github.com/zurb/ink"><i class="fi-social-github"></i></a></li>
      </div>
    </div>
  </div>

  <div class="medium-4 columns">
    <div class="profile-card">
      <img src="img/profile/iu.jpg" alt="Yeti">
      <div class="profile-info">
        <h4 class="subheader">O.G. Yeti</h4>
        <p>O.G. stands for original gangsta.</p>
        <p><strong>Used in a scentence - </strong>those tru gangstas from way back in the day that jacked ya and left nothing but you boxers and socks. Also, Biggie and Tupac were both O.G.'s</p>
        <p><a href="http://foundation.zurb.com/forum">Forum</a> | <a href="http://foundation.zurb.com/">Website</a></p>
        <ul class="inline-list">
          <li><a href="https://www.facebook.com/ZURB"><i class="fi-social-facebook"></i></a></li>
          <li><a href="https://twitter.com/ZURBfoundation"><i class="fi-social-twitter"></i></a></li>
          <li><a href="https://github.com/zurb/foundation"><i class="fi-social-github"></i></a></li>
      </div>
    </div>
  </div>
  <div class="medium-4 columns">
    <div class="profile-card">
      <img src="img/profile/taeyeon.jpg" alt="Swoll Yeti">
      <div class="profile-info">
        <h4 class="subheader">Swoll Yeti</h4>
        <p>Slang for "Swollen", as in getting "swollen" or "buff" at the gym.</p>
        <p><strong>Used in a scentence -</strong> Yo, after a couple months at the Y, I'll be a swoll Yeti.</p>
        <p><a href="http://zurb.com/patterntap">Patterns</a> | <a href="http://foundation.zurb.com/apps/">Website</a></p>
        <ul class="inline-list">
          <li><a href="https://www.facebook.com/ZURB"><i class="fi-social-facebook"></i></a></li>
          <li><a href="https://twitter.com/ZURBfoundation"><i class="fi-social-twitter"></i></a></li>
          <li><a href="https://github.com/zurb/foundation-apps"><i class="fi-social-github"></i></a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!-- 푸터 -->


<footer class="footer" id="contact">
  <div class="row">
    <div class="small-12 medium-6 large-5 columns">
      <p class="logo"><i class="fi-shield"></i> CoreaRoad</p>
      <p class="footer-links">
        <a href="#">Home</a>
        <a href="#">Blog</a>
        <a href="#">Pricing</a>
        <a href="#">About</a>
        <a href="#">Faq</a>
        <a href="#">Contact</a>
      </p>
      <p class="copywrite">Copywrite not copywrite © 2015</p>
    </div>
    <div class="small-12 medium-6 large-4 columns">
      <ul class="contact">
        <li><p><i class="fi-marker"></i>1595 Spring Street New Britain, CT 06051</p></li>
        <li><p><i class="fi-telephone"></i>+1-656-453-9966</p></li>
        <li><p><i class="fi-mail"></i>contact@emperor.com</p></li>
      </ul>
    </div>
    <div class="small-12 medium-12 large-3 columns">
      <p class="about">About Star Wars</p>
      <p class="about subheader">Strike me down, and I will become more powerful than you could possibly imagine.</p>
      <ul class="inline-list social">
        <a href="#"><i class="fi-social-facebook"></i></a>
        <a href="#"><i class="fi-social-twitter"></i></a>
        <a href="#"><i class="fi-social-linkedin"></i></a>
        <a href="#"><i class="fi-social-github"></i></a>
      </ul>
    </div>
  </div>
</footer>
<script src="js/vendor/require.js" type="text/javascript" data-main="js/main.js"></script>
</body>
</html>

