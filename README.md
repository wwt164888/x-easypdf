<p align="center">
	<img src="https://images.gitee.com/uploads/images/2021/0621/111332_1f43ae97_1494292.png" width="45%">
</p>
<p align="center">
	<strong>一个用搭积木的方式构建pdf的框架（基于pdfbox/fop）</strong>
</p>

#### 更新说明
> master分支将作为稳定版本发布，develop分支将会不定期进行更新，欢迎大家提供宝贵意见，QQ交流群：15018726

---

#### 项目主页
> https://x-easypdf.cn

---

#### 项目概述
> x-easypdf是一个基于pdfbox/fop二次封装的框架，目前拥有两大模块：【pdfbox模块】与【fop模块】。【pdfbox模块】主打pdf编辑功能，以组件化的形式进行pdf的构建；【fop模块】主打pdf导出功能，采用数据源的方式对xsl-fo模板进行转换，同时提供java对象的方式构建dom模板文档，即使完全不懂xsl-fo的语法，也能轻松上手写出对应的模板。两个模块均可单独使用，也可以结合使用，帮助开发者快速生成pdf文档。

---

#### 当前版本
> v3.0.0

---

#### 使用环境
> jdk 1.8+

---

#### 项目特性
- ##### pdfbox模块
  - 简单智能

  > 自动换行与分页

  - 表单填写

  > 内置文档表单填写器（可填写表单中的文本域）

  - 图像转换

  > 内置文档图像器（可将文档整个转换为图片，或将文档中的某几页转换为图片）

  - 文档提取

  > 内置文档提取器（可提取文档中的文本与图片）

  - 文档替换

  > 内置文档替换器（可替换文档中的图片）

  - 文档分析

  > 内置文档分析器（可分析文档中的文本与图片信息，包含坐标、内容、字体大小等）

  - 文档拆分

  > 内置文档拆分器（可将文档按照页码拆分成一个或多个文档）

  - 数字签名

  > 内置文档签名器（可对文档进行数字签名）

  - 内置字体

  > 内置细体 | 正常 | 粗体三种开源中文字体（华为鸿蒙字体）

  - 组件丰富

  > 内置水印、页眉、页脚、文本、图片、表格、矩形、圆形、线条、布局、条形码（一维码/二维码）等组件

- ##### fop模块
  - 基于模板生成
  - 内置多种数据源（xml 数据源、thymeleaf 数据源、freemarker 数据源、jte 数据源、document 数据源）
  - 提供面向对象的方式生成模板（document 数据源）
  - 灵活的扩展性

---

#### 软件架构
![软件架构](https://oscimg.oschina.net/oscnet/up-96975e855f8b12ae0d0d65f409fb63d1ebc.png "x-easypdf整体架构")

---

#### 代码扫描

- ##### SonarQube
![SonarQube](data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABLoAAADSCAMAAAB6rll2AAAA/FBMVEX///9ASFsAqgAzMzPn/v7//ur/zp9Ln9T//9XP//+Qzf+u6P9rsermsYDNk1v/6LpAk9WvcFtAcLqQSFtASaBASYBrSFvMzMzX9dh1uOzrt3UzM5w1erf/6bK3eDSc1P6QSIB1NDMzm9FrSKCQSKBrcLeQcLrRmzW97/+F1oX28devcIBIwkic3pyydqBqotacMzNrSIAZshlayFqu1fHfxJiKveHS8+0zNXWblMHr5r1/rrrMlHpANEPI6b2UoACv6N2F1e9C0vSusH/W1gAAoXMA19YAs6yQgF2ZWzOvx8B1cIG0rZzu2HUzuzNrk6C9xwBra2WQk4DYoE0xPNV8AAAAAHRSTlM2uXDMAAAZqklEQVR42u2dCXvTVtaArcYWtizJsp2NkDZMIEANJV2yNAlQKPNNWTrDtPP//8t3z7mLJNuEbHYS/L4PJF603Mi6r845upYaDQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPh6aLVax2t//vnur0Hzc5O8OGk2Wi923bONpc2mzjcys/31oMkmBFhc4qWlu9YLS/c6M1nyFGl9t1Tl5Vaj8bj2ypad7t8vl3bNO1G3oq6NMNcbPjyAhVeXqMOry9rh7vkWMmm+L6hr5a+//nq5dLJ22Grq+jd9FPXtP5y6zIOVJ43j90vLT2rqevnXx5MPhwRdAKir/WNQhw+Jzq6uEETdPbu67tYkNTXqMs6KtmzsJSmlFer2Ruk4AFh0dRmJbDdCzCVyiM+sLjHXti4iiOgi6hqPur59v7Rkq1wvlpZ2OqgLAE5T1+MxA51lCdvud80qp6pr97t6kDURde0bE94p3WgyR6s31AVwqzGy+fWJ9Opfn1Rf06fy23rDJIJLS/fWf9RClLhE/m+4lEyUcG9fJrTi2PbqCnLY8MupzBYWbmY3c9/reNdpW6wD9Wd1VaFpn1PXlD9v6Y5vxshMPGi239fK9MG1AHDLwqVt6chlv9/wIoh96clqxtvlsXkUl7pQrdnpqurSeewoBKcX88TPZgwYFm7fvnf4nS/Qy1rq6gpm2qg5aiJhjJfGMA19WXka/bnzpBFrzQt1AdxyHi/9asKp7WqgcrcsGhk9bDat2Z53VCXtH61PTPCjud2G9H7xTz1hdB7ZVt/Yn3fltXsdmXarERbualztHz+vLmu6zWa9adNrXdv+dbPALR8wOjReE78u722QNQLcbmwy2HGu2Q7xSHjJxljSySW3M0+stFyu56KliVpXyBNbdgI9+WinikP26EwmSjlNXW5VnWrTRF3vNXOthk9Tx3VtWGdthCz45ctf36IugNufMpYqKdX165ONIIrHZcK39dg9+LK67ADVw+nqCgt3agux2LRa15i6XLXLLHJSXeNRl+F/1nVWXf+Tv2T/wFirgboAbnvUVSnSh7qXjYMe+34vBjEv7X5XBk6io9Zp6pJFTFdXuXC3AJdXNkJquO3qcJURYxv1UrzIKfYh2MZn1WWe6neENpbumb9yI3onwnpsk1jUBXCLa10f/lHpw8Ya6qItrSypYja2vJU2ykzyXsfa5rFz2vjgiK2GG9ylVa5Q63LqKhfu1VWO6zIe9WYLtS5dVWiab+nLPVGXSRzToK7JhPH4H3Z4hA2+9jc3bIHtLuoCuN3p4t36ICzX/bd8gTskam5Iw3ajUT1VaE8/Lk0Z1+Vmqp9hLKOuesJYKahvVWbfrq4qNM2uYt+EUaIu46Y3Tl0+zKqOBHvhvqdo48fRExHW8Y8vt1AXwK02l9VPJWWMnWh0YPtjry7rFyc5M4kMqdLXRDmb++Pqir2uGmPjutzPsPCgLjfdttfP0uaxq3WFVYWmOTsuu8EXL08OfcRYHxshC7VfAAqTq7D2bfKIugAWAx3O0DhtiPull19x6OmM3ksiOPrT6upXZ6+xqGv04qUOodfzkV6km2bWooG6ABYrvZyhuiSEO6u7Ru9f7m3IiNOVg9b+O4nGJoekHpp4TS4YYXPWXRfbbR6bWVEXwKLwOGRrM1OXq3id6euPo6Pm6N1HF22NZKT8lDBOLjNoU9Fo16elm83RUQN1ASyQulxfn526rCC32NgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHMhG9TvR5gn8Uq38rzVT+TVotFbjZSCbQbw9dK+L/2/96Az9orVwVN52H4ob8aDkXsjjpxF7Bv6SqAIFqlZpdVP7WO/plY/SqdayK5zSkOH9YXmqazfLCaKljvlSjNvrLgoV28nkd+yzjyKZHWiuFJvWSR/VVaXXhwl7s9brvylqS7Tm1RmkcXlkd8ssuBE1xZFUcIuBnAG8vQ0Q00h1v6WpV4iblL7cqP3U9c7QdWlEU3o2UFdldiosMtsDysSMKGQLsmrSwWRjlvIRkvJpLqcBCyDZvXpymvTOGtDbWZWLrVU137XxmymGeK/2EwXL3daR81GEKq8oM2sirc9HMj20Ka7mK99ZB2aRSc7Xl12EbJUuwBZcPWvBYCrV1euFrGiKaRbFjppZuIIn3hZEqsu12GdmSzLnbq6Qt+3HbjVLyoRjKqrmCIls0ZV4TR1JVXTugmsMlqqrre+keZFGwGmFXW51eomcKv2myO0RLab84z/G73KdaLq9tNt3BtT11ig5/6ILGWXBJiJujRU0blil+uZSeNiWnhWUZcXj0uRSrlINzdzqxJydVpv1aeKq4Om6nCwX9R7uO/gRl3eicVZ1KWJ6HjUpW23v/NyKabhGkg6Pfl4MaxZNo9bT3UTyuZxRbS07jPdEhKrlXbStthwywWtn411AaBRK9dID4s1hNG+lqfumQ05bJ4WD/ZDXUbV9VEdY/p6XA2z3LNBM0+lU8qzlQ+rUZns5QcP99NJdZlVSV6VR5tDM6FPnWwDEwnzksyvomYhJxRt6ZiBywAw9aFaYtYkaZ/Wutbvd0t1lVFXRV2yISrqGgu+quqq6WaauuwiSnVFvoKmBbSfNFwtqtk4AJxqLtNPYumzpj+ZPiPdxvQy90yzQPNQiz3Ss1y3tlGXKiQbj3S048VBXdrzd94lLuwwndmoJk/HEj/13/L60KaHknhm1Qp+++ODw6d/F65X1yzk1SU/8lMSLWms+TPMAvqJSxg7EoxNRl1j26ZUV2xz3LLUVVFXbTNMUZc7XVCNP3urSaPMjNVZK93G5OkKAJjSo30Pk05murfKqqg8Cz2zGn7YWpctPafj6hqPulZMwrifupKU6afyIC8mEsbG4dRsVafMTlyty6qraiFnLW1YXm9LrbAmU5mZeg/eDppeXaLfU9SVOQlXgqVgLjFrYtX1NPjLnYSsqiupmaumLq+7SlHe1sMo0gN8EeeHNJyU12DKP5MsTidw8Ziv9uTutJx5qmlbLWGsR12FWVw8OH7QkY6qFSwbIBkP1MML6fevh772HrqwqKv98NUDWx4KjQgWcsuzZbLxqKv3IFSV5KGaWE4K7k7WuibL9Pb0oE6m1hyPudzMOkE9x8t9wOXTyDBEo66udKq6cor0AGeLuqQ75qFzHZvu5Z6FqCsei7rKEQVfiLoyVZcJ4z6ZOU2KFPsTgyY5qqirff+Dq8uXRSMblOhJvAOzXp06NCJYKNXzlm9qMU4trsmsQjI71EKq/q3+m/q4rkq0Vda6/GBWPzhi0lyVwRF13UwMjgiDy3z41vpgJ3jeseNhTcMO3LlVivQAZyGv1LpsTejvNIQY9VpXEjpuRV15emqtK5cgTv0SpgvjuqrqCmO9Qs/1YyRSF5mU0U3NQieroQY3OaZVpxb3/fR6KGX+IjaSS9sPDz87rivz6rInLTU2LIeKGuPWz2Rm9nFtbK4fMhKHsxqZC+kytwxbppfYMraTmr9GVqDRZsZ4foAzuct0HvFPFtmulqm17LNwhlEDnjz02TycMFQ9RdMSRj9kovX0g8kWTS7oc6qKuoIyYhVoVMmx/ICuVT+uyw7FMNO1axbqBEeNjZxvD23eqbJxIyukDm4cUR0gJn+tV1fGOHaAr45qOScvC0S1Io+LumJxXG/VaMEkSCaMOxlqEdx9baeMsOLKyFI5gRmGrC4f9lUpIcDpPXilVSJV3LiF9BzdRFsAABqMNgIA1AUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADA71r9/9vM33/z87Pt1tsVNp7l3sHvnzm6612RbwKKL65dvAr8gr5stroM7gQPktWhHeFpQ44dvavzALnpz2btTY48tskhHeFpQ53tpxrMfTDvWf3gmj79nL70l5sJdC3WEpwWTrfnPK//s1X+Iu26RuXDXfI/wVb6nBdcb5axLyFV9QQIv6l03kuadKVDvumLyKFrpfinemGnMsaAtyAfVfbm3utyxLSn06U8T7TG563/qr5i46xd24FnQW03co7iyY/RWo0g/NHkQpeZBeyg/W/3xvedgmroOrnjHkVYUuvrItWvBzGX+5myi365/M4V1WnBlLcjG9rZW/6OoKxs0W/1UnibTgq5X9ZdeEXbNgvbQfDiJl0O5X8TmxVbffGrthx15L/Xqysf3HRt0/d441t9Fc3R1YVe544he20PjrvZRw7ZrwQ4vcnRv9YvJI/wkv9CCK2tBttypRV3Z8lujLjVWLCKd3BG/H0sXXcpIpX4G6kpd1GV+lVGX3UPKcNh8hFZdk0e9Paeu1pH83mqMrq7aVe44eVENCss4cfZkxSnvaePaw8RtM41OjW/NxoqLehB72VbomjJNVqYFHP/35NsZBz0L24KqusyOpz3hflfVNSVdFE39MCWlfYZq5pQwjqsrduqKowlpHDh1Ndc06DocXWnGmFfsYMO+OavrNOufPGrqgTjxkjMhqoSF5pFRV+9B56pW5WKNcReW9el/jdZ/m2mVenFbUFGXJIlZJerK08nJf56iTaPXn/HMvGpdGl2Vn5qLujRxHGPXqqu1J876fbTl1LV7lTuObVhIFq4wnLmcvB7p9smcutJG67naqrfTjAs9NF9Zt00bjcmi8LPQU7v/7P47PHk2E3Esagsq6pK4T6O+XGpdSTytbmHWfcYXYTbqMtnPwbAehJkoYzgle7rj1HVgwq7iybFX150rVZfNEvLCNTid39bJJNSMVrqx1v0O+3K2oPW0O0VdxlXuMBybeTb7ydV327Fo82ffUf9Y/+1fvRBynOMIv0YLvtSCUl1qTVWX1gZMKtBbnTjlibquW11av08rRSd9ZVq049V19GQkP09V19ol1WWeTNlbzsPaBdQlDShEV7Ge43zana6uRu5aJmc24is9DfqZiKPMlb795o/D/4anMxTH4rUgqMs2oKy15YXUMeKx2hsJ47UnjFFadj9b4WoPd/vLnc8mjEfWXqcmjJdVV+H2oYvL6yLqkmPsoGkevOhKBFaW++rqMiGXJom2iFvkxczrPGWB+p8mX/r3bMWxqC0I6pKPXnGx/4OOROLj4yMo01+zumxF3GVmsQ2/zGvt4WQsceDVdefJofmxdVqZ/sLqqte6Js+Qz1JdsnP2dpq9neOdpuys+XR1aShmHtoalwnT+leY1uZTz675ZOkPbUb7txmma4vbgvqQ1LB+2Svk7PO4uhgccc3qqrrCl5ZEZ73VCXftBXX9LgMjtk4bHHFRddkmhjOM47vTbNUlLpIC7Yu/dWc1WcKUhNG4TaKuYeKq83ERBk1cBfp5TBjbl6jffOvDjvOWqNdowQXVJXvEtKiLIanXrC77mgYOoSiu5oij8YCnGdRVPFkr1dW8SnXZHTY0b75Rl4zU+rTTdANEouV3Tl06mtckkpJDJEZdtjqY2ZRCx3VFV+YuHYU7HnD4gQGuw7759vwDA9ZowdnU1fZnqFwDtOQ2rdbFF4GuUV3yKbW0qiUj53urRS2JzKJ0asZ4pi8CXVRdagE7JFbami935qmum4DU2yb+ajcc8182Tfqj9d9zD8dcowWntiDz1a0xdYXTndMbxNev54CGDvoNIP2uouwb9lPK3Zdw8si97PO1fDzuOsfXr88pjqwsi8bui5T60iVO3p1XXT43vJlc+kswa7Tg8i0Yg4ve3B7OftGbtWtv6zlbMOHpm8Wlv3q8RguufqfkUoO32F1789pLZp8w3mwue8GXNVowg12CCzzfWnft3VxxfGXquuxl9tZowSx2CW6rcXs44201UNcNO8Kv0YLZ7BLczOwWyessNzNDXTfsCL9GC77CXQK+RnF8jfvpJY7wa7QAdQHq4gOhBUBPYT/lA2GXAHoK+ykfCLsEsJ/ygdACoKewn/KBsEsAPYX9lA+EXQLYT/lAbn4L9H4mcmeAPJ1PC8IlU+XCRld5BxW4EvKkEae9XXd9iU+r7pNqZKm9pkM6h70k3HZF9057IYfnnThtP2pe5KIOqOvSm8N8BnG41HGeumuPXPRyHudtQZaUFztRejt6Hd3CXfkkOvdF0i68S7j7P4Wrq0TRCgq7GejtIVJ7RXh3QdDGftfvLPPqKf4erkahvV13kb9H+ycPO75Ji6Wu+CKHjKvdHO37f9rPRK6nmJ/Yj+FCn8aF1CU/X3StMdKwN8orwSUz3wbBk6LK7GNSVxlcN3KVPaMu3SviRIOw1lHzMrdGXTt3Ly3DcrP2zF6f1B52B/tzPcTeGHUVV5mfXHBzxJUbTLQfadBxMhd1iTKWO05XeRKneXmji/hiQdfFtoFoyv3v7brrykXLqOtmoFfd7n0aykeiBxSjMBP6mFistzunnmIP5bpy01/lKpl2B+k9jxK5um6Wzqmv3ih1VW7scS0tGDRjfxWz3k/d9uWirnMfT5OG3wCiLhd19VaXO3FaRmVz6BwVXWVEXTcs6IoOhj59N0e05cPnmj9KIvlqdU6pvSYGaspspRv7m7fGg/3d5/tH6TX34etSl3TW3EbBcq38t3pB/DleEjG2Aa+GOO37rx8dSyi0Pkd19T7ufOzW1CXauGjUdTF1lVFXuJovUdcNof3wXWLrXXofi9bzw777eOxZHPvOHGIM7abth393jcdUXUZlcRon7aNFPMy5G0Fadckl0uUecw/nvB3kY/FhVvvR8U6z/XBu6nrzoZEfyDpFXZ/8MVTUdW1RV6jHwo2gt6cxjY17jnadJswOm0fzU5dWuPSOanvaEHepeHcb6g87zYX7WDRhTJ269DOQO0UOmnNtQnwydGcVxVnzVZdE37IXJjbqMg5JriHqqiWIRF03jdwOhFCPGXW91WrszvFuNkd1mT3THdFevPYHuhM3SiOOFi9ftHdJK5y6NFnK9I5zczzuZ0l28uj406PR0aNmb2c014QxGzR7D+Tksh5DI2PtE00e5x11laeQ9MChJ95/YmjEjVKXnE80fcRHXdJZ5qwuF1FUoi4Tdchwr2xR1WX+fncv2767x1tjnjnji+6L14+er8u/pgywk+RtTmX63oHGOrKyLNFan9H3aJhcZ9TV27P546CJMm6UuuQ497Qbbjinypibunqrepc1XXU1YTQR+srr/mA0XMAyvR0LYO9lqzcCfKujQue3JdoPX0mo9enR8YnkbXM+w9iorkwCHb15b5LN1xy1cTu5PWFlPguGpN4odTVkr7BRlxxbEpuhzOOcVux3R9M1k4q6NDvSm3iO3zV9kT+mudHbzRLN2Pajon3fDo6Y7ynOmift4IjdUHBCHwC3gkuMEAYAuJ6IiygDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAG4JvdXlTnu40rXP8rTRHi53Gtmgqe+ZX73VSDAvfobMvJs2Wv0ooDObhZnfWeEnSu2DWN6Wdxp5WK60wcyfNHoP9JksInfLSrQdqV98oauLVj58qVUAsDjqMtYoRF25VUYcOfG0h15HJeY1izjpuNnqe/2p8KykUrPANKgrdpO3RmurZuLcrEM9ad4YNKUd5iVdiLyTh0cys1t2Juoyj+V/ezjRJABYEG3ZyMVaaOV1JXDS0MmHStPM5awTppmIuszCE/lprWPVVfiZW/3BfqT+kYXYN20klpbqkn/yKEqzIEAfdb0eBm0CwGJHXa1+mX+pW3zGlk1LzIx13gZZFY3xqMvHW7EYSh25vO/V1eqrgYqGmyuvKlNlNaauMjf1UdfrYaGJJB8gAOrqlzLa7A+aRk6HqpZsWnRTj7pcRcyx0o1tymnzxsSGUyHqGu2bECvW2Cxz9aq4akerrtf9oC679JVuNeoqsuVXqAtgEbFJnk8YRSWZDZ16q4ULn05V1/owqGpqKlqazKzB17o0roq92MqWhJRUW1OMqcuGdVlIOduoC4CoK5xhzMoMUI1l3n1XOdU3oS71jWZ9k2cYtfDuali63COzVF/DL6fXVce1eV3UpecLxtXlpHiCugBQ17ASNrlE0OZtmXfYtKhrMDLqMlmgU5eZTef1p/6stPRnb/WjSxidumJJR+2i5bkEWtIE8zsJCeMHM+nUqEtKXO3hZh91AaCukKqVwZAGRsnn1GUkJOoy730m6vLaM8/aD9+qulKvLqOj0TsZyKWnIc28y2/NdOZ32gjq+ltHd03WunRMVxLOAwDAohFH1VpXlGi8ZKMuHVblK1BT1RXLELBBs/+5qEvHmDqBtQ5lEZkKx0ZZZjozwcHQnmY0VrLqS1y4dyRRmXOgi7q8DbNoRYIte9KSEakARF2NcXXlvnhfqiukky5Cyrxw4vr4BpnZaa+w0klVblZd9sU8DKZ3nnTjtMyjQt7dNM3J0/HBETKXhmHyzkQFDgAWWF25G5G63ImtRZJKrOUf6IhSHdEVop+yThZOGTrxGHXprKouDcjicD6g9byjuWqSRTYTTDR3tUGdnGX06hJdtoeFlM/sKcqMlBEAdY3px5WlQuJmc7kwZW83VJtaUjGvDSt9ozGZBlK+8JXmNsISdb2QcRVuLJkbslq4lduXbYtyVVteGTlWNEbvksoY1olRGQAAk/DFQQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgBr/D6taWsEd2SzqAAAAAElFTkSuQmCC)

- ##### Gitee Scan
![Gitee Scan](data:image/png;base64,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)

---

#### maven坐标
- ##### 全功能模块（pdfbox + fop）
```maven
<dependency>
    <groupId>org.dromara.x-easypdf</groupId>
    <artifactId>x-easypdf</artifactId>
    <version>3.0.0</version>
</dependency>
```

- ##### pdfbox模块
```maven
<dependency>
    <groupId>org.dromara.x-easypdf</groupId>
    <artifactId>x-easypdf-pdfbox</artifactId>
    <version>3.0.0</version>
</dependency>
```

- ##### fop模块
```maven
<dependency>
    <groupId>org.dromara.x-easypdf</groupId>
    <artifactId>x-easypdf-fop</artifactId>
    <version>3.0.0</version>
</dependency>
```

---

#### 安装教程
```cmd
mvn clean install
```

---

#### 快速体验
- ##### pdfbox模块
```
// 创建文档
Document document = PdfHandler.getDocumentHandler().create();
// 设置使用字体
document.setFontName("微软雅黑");
// 创建页面
Page page = document.createPage();
// 创建文本域
Textarea textarea = new Textarea(page);
// 设置文本
textarea.setText("Hello World!");
// 渲染文本
textarea.render();
// 添加页面
document.appendPage(page);
// 保存文档
document.save("E:\\PDF\\pdfbox\\document\\hello-world.pdf");
// 关闭文档
document.close();
```
- ##### fop模块（使用document数据源）
```
TemplateHandler.Document.build().addPage(TemplateHandler.Page.build().addBodyComponent(TemplateHandler.Text.build().setText("hello world"))).transform("E:\\PDF\\fop\\document\\hello-world.pdf");
```

更多教程，请查看[文档](https://www.x-easypdf.cn)

---

#### Dromara 成员项目

<p align="center">
<a href="https://gitee.com/dromara/TLog" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/tlog2.png" title="一个轻量级的分布式日志标记追踪神器，10分钟即可接入，自动对日志打标签完成微服务的链路追踪" width="15%">
</a>
<a href="https://gitee.com/dromara/liteFlow" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/liteflow.png" title="轻量，快速，稳定，可编排的组件式流程引擎" width="15%">
</a>
<a href="https://hutool.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/hutool.jpg" title="小而全的Java工具类库，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。" width="15%">
</a>
<a href="https://sa-token.dev33.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/sa-token.png" title="一个轻量级 java 权限认证框架，让鉴权变得简单、优雅！" width="15%">
</a>
<a href="https://gitee.com/dromara/hmily" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/hmily.png" title="高性能一站式分布式事务解决方案。" width="15%">
</a>
<a href="https://gitee.com/dromara/Raincat" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/raincat.png" title="强一致性分布式事务解决方案。" width="15%">
</a>
</p>
<p align="center">
<a href="https://gitee.com/dromara/myth" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/myth.png" title="可靠消息分布式事务解决方案。" width="15%">
</a>
<a href="https://cubic.jiagoujishu.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/cubic.png" title="一站式问题定位平台，以agent的方式无侵入接入应用，完整集成arthas功能模块，致力于应用级监控，帮助开发人员快速定位问题" width="15%">
</a>
<a href="https://maxkey.top/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/maxkey.png" title="业界领先的身份管理和认证产品" width="15%">
</a>
<a href="http://forest.dtflyx.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/forest-logo.png" title="Forest能够帮助您使用更简单的方式编写Java的HTTP客户端" width="15%">
</a>
<a href="https://jpom.io/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/jpom.png" title="一款简而轻的低侵入式在线构建、自动部署、日常运维、项目监控软件" width="15%">
</a>
<a href="https://su.usthe.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/sureness.png" title="面向 REST API 的高性能认证鉴权框架" width="15%">
</a>
</p>
<p align="center">
<a href="https://easy-es.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/easy-es2.png" title="傻瓜级ElasticSearch搜索引擎ORM框架" width="15%">
</a>
<a href="https://gitee.com/dromara/northstar" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/northstar_logo.png" title="Northstar盈富量化交易平台" width="15%">
</a>
<a href="https://hertzbeat.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/hertzbeat_brand.jpg" title="易用友好的云监控系统" width="15%">
</a>
<a href="https://plugins.sheng90.wang/fast-request/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/fast-request.gif" title="Idea 版 Postman，为简化调试API而生" width="15%">
</a>
<a href="https://www.jeesuite.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/mendmix.png" title="开源分布式云原生架构一站式解决方案" width="15%">
</a>
<a href="https://gitee.com/dromara/koalas-rpc" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/koalas-rpc2.png" title="企业生产级百亿日PV高可用可拓展的RPC框架。" width="15%">
</a>
</p>
<p align="center">
<a href="https://async.sizegang.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/gobrs-async.png" title="配置极简功能强大的异步任务动态编排框架" width="15%">
</a>
<a href="https://dynamictp.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/dynamic-tp.png" title="基于配置中心的轻量级动态可监控线程池" width="15%">
</a>
<a href="https://www.x-easypdf.cn" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/x-easypdf.png" title="一个用搭积木的方式构建pdf的框架（基于pdfbox）" width="15%">
</a>
<a href="http://dromara.gitee.io/image-combiner" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/image-combiner.png" title="一个专门用于图片合成的工具，没有很复杂的功能，简单实用，却不失强大" width="15%">
</a>
<a href="https://www.herodotus.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/dante-cloud2.png" title="Dante-Cloud 是一款企业级微服务架构和服务能力开发平台。" width="15%">
</a>
<a href="https://dromara.org/zh/projects/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/dromara.png" title="让每一位开源爱好者，体会到开源的快乐。" width="15%">
</a>
</p>
<p align="center">
为往圣继绝学，一个人或许能走的更快，但一群人会走的更远。
</p>