<template>
	<div class="mainTop">
		
		
		
		
		
		<el-form ref="form" :model="form" label-width="80px">
			<h2 class="titleH2">栏目选择</h2>
		  	<el-form-item label="栏目">
		    	<el-select v-model="form.column" placeholder="默认">
			      <el-option label="区域一" value="shanghai"></el-option>
			      <el-option label="区域二" value="beijing"></el-option>
			    </el-select>
			    <el-select v-model="form.column" placeholder="默认">
			      <el-option label="区域一" value="shanghai"></el-option>
			      <el-option label="区域二" value="beijing"></el-option>
			    </el-select>
		  	</el-form-item>
		  	
		  	<h2 class="titleH2">资讯内容</h2>
		  	<el-form-item label="标题" >
			    <el-input v-model="form.nr"></el-input>
			</el-form-item>
			<el-form-item label="显示导语">
			    <el-input v-model="form.lang"></el-input>
			</el-form-item>
			<el-form-item label="内容">
			    <el-input v-model="form.connet"></el-input>
			</el-form-item>
			<el-form-item label="封面">
			    <el-radio-group v-model="form.fm">
			       <el-radio label="自动"></el-radio>
			       <el-radio label="单图"></el-radio>
			       <el-radio label="三图"></el-radio>
			       <el-radio label="广告"></el-radio>
			    </el-radio-group>
			</el-form-item>
			
			<h2 class="titleH2">标签</h2>
			<el-form-item label="标签">
			    <el-input v-model="form.element"></el-input>
			</el-form-item>
			
			<h2 class="titleH2">文章设置</h2>
			<el-checkbox-group v-model="form.active">
		       <el-checkbox label="美食/餐厅线上活动" name="active"></el-checkbox>
		       <el-checkbox label="地推活动" name="active"></el-checkbox>
		       <el-checkbox label="线下主题活动" name="active"></el-checkbox>
		       <el-checkbox label="单纯品牌曝光" name="active"></el-checkbox>
		    </el-checkbox-group>
			
		  	
		  	
		  <!--<el-form-item label="活动区域">
		    <el-select v-model="form.region" placeholder="请选择活动区域">
		      <el-option label="区域一" value="shanghai"></el-option>
		      <el-option label="区域二" value="beijing"></el-option>
		    </el-select>
		  </el-form-item>
		  <el-form-item label="活动时间">
		    <el-col :span="11">
		      <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
		    </el-col>
		    <el-col class="line" :span="2">-</el-col>
		    <el-col :span="11">
		      <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
		    </el-col>
		  </el-form-item>
		  <el-form-item label="即时配送">
		    <el-switch v-model="form.delivery"></el-switch>
		  </el-form-item>
		  <el-form-item label="活动性质">
		    <el-checkbox-group v-model="form.type">
		      <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
		      <el-checkbox label="地推活动" name="type"></el-checkbox>
		      <el-checkbox label="线下主题活动" name="type"></el-checkbox>
		      <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
		    </el-checkbox-group>
		  </el-form-item>
		  <el-form-item label="特殊资源">
		    <el-radio-group v-model="form.resource">
		      <el-radio label="线上品牌商赞助"></el-radio>
		      <el-radio label="线下场地免费"></el-radio>
		    </el-radio-group>
		  </el-form-item>
		  <el-form-item label="活动形式">
		    <el-input type="textarea" v-model="form.desc"></el-input>
		  </el-form-item>
		  <el-form-item>
		    <el-button type="primary" @click="onSubmit">立即创建</el-button>
		    <el-button>取消</el-button>
		  </el-form-item>-->
		</el-form>
		
		
		
		
		<el-row>
		  <el-col :span="24">
		  	 
		  </el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		data(){
			return {
				form:{
					column:'',
					nr:'',
					lang:'',
					connet:'',
					fm:'',
					element:'',
					active:[]
				}
			}
		}
	}

</script>

<style scoped>

</style>