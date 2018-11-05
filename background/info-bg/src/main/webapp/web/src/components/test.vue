<template>
	<section>
		<div>
			<h2>过滤器：</h2>
			<p><strong>时间戳：</strong>{num | time('YYYY-MM-DD,HH:mm')}} <span class="pl30">例子：num | time('YYYY-MM-DD,HH:mm')</span></p>
			<p><strong>转大写：</strong>{{str | uppercase }} <span class="pl30">例子：str | uppercase</span></p>
			<p><strong>转小写：</strong>{{str2| lowercase }}<span class="pl30">例子：str2| lowercase</span></p>
			<p><strong>文字过长省略：</strong>{{text | truncate(10)}}<span class="pl30">例子：text | truncate(10)</span></p>
			<p><strong>文字标识符：</strong>{{moneny | currency('$')}}<span class="pl30">例子：moneny | currency('$')</span></p>
			<p>
				<strong>控制小数点：</strong>
				<!--<p>{{numBer | number(3)}}</p>-->
				<br />
				<span>{{numBer | number(3)}}</span>  <!-- 小数点3位 -->
				<br />
				<span>{{numBer | number(2,'.')}}</span>  <!-- 小数点用 ‘.’-->
				<br />
				<span>{{numBer | number(2,'.',':')}}</span>  <!-- 分隔符点用 ‘,’-->
			</p>
			<p><strong>驼峰转换：</strong>{{varAble | camelize}}<span class="pl30">例子：varAble | camelize</span></p>
		</div>
		
		<div ref="form">
			<h2>常用方法</h2>
			<p>获取地址栏后面的参数：this.$own.getHref('name')</p>
			<p>时间戳转日期：this.$own.dataTime('时间戳') </p>
			<p>设置本地存储(localStorage):this.$own.localSave(key, value)</p>
			<p>获取本地存储(localStorage):this.$own.localFetch(key)</p>
			<p>删除本地存储(localStorage):this.$own.localRemove(key)</p>
			<p>增加class:this.$own.addClass(obj,className)</p>
			<p>移除class:this.$own.addClass(obj,className)</p>
		</div>
		
		
	</section>
</template>

<script>
	import moment from 'moment'
	export default {
		data() {
			return {
				message:'sdds',
				num:1510034252,
				str:'sdasdas',
				str2:'ASDASDAADS',
				text:'文字过长怎么办文字过长怎么办文字过长怎么',
				moneny:'30',
				numBer:2423423.32423423,
				varAble:"webkit-box-fiex",
				
			}
		},
		created(){
			//console.log(moment);
			//console.log(moment().format('YYYY-MM-DD, HH:mm:ss'))
			
			console.log(this.$own.dataTime(1510034252));
			
			 this.$refs.form.style.color = 'red';  
			
		}
	}

</script>

<style scoped>

</style>