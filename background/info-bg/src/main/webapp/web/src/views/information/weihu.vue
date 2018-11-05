<template>
	<div class="mainTop">
		<!--头部-工具条-->
		<el-col :span="24" class="toolbar paddingBB0">
			<el-form :inline="true" :model="form">
				<el-form-item label="频道名称">
				    <el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getUsers"><i class="el-icon-search fontIco"></i>查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary"  @click="handleAdd"><i class="el-icon-plus fontIco"></i>新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="users" highlight-current-row v-loading="listLoading"  class="width100p" >
			<el-table-column type="index" label="序号" width="200"> 
			</el-table-column>
			<el-table-column prop="name" label="子频道名称" >
			</el-table-column>
			<el-table-column prop="sex" label="子频道代码" >
			</el-table-column>
			<el-table-column label="操作">
				<template  slot-scope="scope">
					<el-button size="small" @click="handleAdd(scope.$index, scope.row)">修改</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		
		<!--新增/修改-->
		<el-dialog title="新增频道" :visible.sync="dialogDisplay">
		  <el-form :model="addForm"   :label-position="labelPosition">
		  	<el-form-item label="主频道类型">
		      <el-select v-model="form.region" placeholder="请选择活动区域">
			      <el-option label="区域一" value="shanghai"></el-option>
			      <el-option label="区域二" value="beijing"></el-option>
			  </el-select>
		    </el-form-item>
		    <el-form-item label="子频道代码">
		      <el-input v-model="addForm.code" ></el-input>
		    </el-form-item>
		    <el-form-item label="子频道名称">
		      <el-input v-model="addForm.name" ></el-input>
		    </el-form-item>
		  </el-form>
		  <span slot="footer" class="dialog-footer">
		    <el-button @click="dialogDisplay = false">取 消</el-button>
		    <el-button type="primary" @click="addYes">确 定</el-button>
		  </span>
		</el-dialog>
		

		<!--底部-工具条-->
		<el-col :span="24" class="toolbar">
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" class="right">
			</el-pagination>
		</el-col>
	</div>
</template>

<script>
	import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';
	export default {
		data() {
			return {
				form: {
					name:''
				},
				users: [],
				total: 0,
				page: 1,
				listLoading: false,
				sels: [], //列表选中列
				dialogDisplay:false,  // 新增框设置
				addForm:{
					code:'',
					name:''
				},
				region:'',
				labelPosition:'left'
			}
		},
		methods: {
			handleCurrentChange(val) {
				this.page = val;
				this.getUsers();
			},
			//获取用户列表
			getUsers() {
				let para = {
					page: this.page,
					name: this.form.name
				};
				this.listLoading = true;
				getUserListPage(para).then((res) => {
					this.total = res.data.total;
					this.users = res.data.users;
					this.listLoading = false;
				});
			},
			//新增
			handleAdd:function(){
				this.dialogDisplay=true;
			},
			//新增-确认按钮
			addYes:function(){
				this.dialogDisplay=false;
			},
			// 删除
			handleDel:function($index,rowData){
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
		          this.$message({
		            type: 'success',
		            message: '删除成功!'
		          });
		        }).catch(() => {
		          this.$message({
		            type: 'info',
		            message: '已取消删除'
		          });          
		        });
			}			
		},
		mounted() {
			this.getUsers();
		}
	}
</script>