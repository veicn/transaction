<template>
	<div class="mainTop">
		<!--头部-工具条-->
		<el-col :span="24" class="toolbar paddingBB0">
			<el-form :inline="true" :model="form">
				<el-form-item label="频道名称">
				    <el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getUsers" @click="seachHandle" icon="el-icon-search">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary"  @click="handleAdd" icon="el-icon-plus">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>
		<!--列表-->
		<el-table :data="users" highlight-current-row v-loading="listLoading"  class="width100p" >
			<el-table-column type="index" label="序号" width="200"> 
			</el-table-column>
			<el-table-column prop="channelMName" label="频道名称" >
			</el-table-column>
			<el-table-column prop="channelMCode" label="频道代码" >
			</el-table-column>
			<el-table-column label="操作">
				<template  slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.row.uuid, scope.row)">修改</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.row.uuid, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--新增-->
		<el-dialog title="新增频道" :visible.sync="dialogDisplay"  width="35%" >
		  <el-form :model="addForm">
		    <el-form-item label="频道代码">
		      <el-input v-model="addForm.code"></el-input>
		    </el-form-item>
		    <el-form-item label="频道名">
		      <el-input v-model="addForm.name"></el-input>
		    </el-form-item>
		  </el-form>
		  <span slot="footer" class="dialog-footer">
		    <el-button @click="dialogDisplay = false">取 消</el-button>
		    <el-button type="primary" @click="addYes">确 定</el-button>
		  </span>
		</el-dialog>
		
		<!--修改-->
		<el-dialog title="修改频道" :visible.sync="dialogVisible" >
		   <el-form :model="editForm">
		    <el-form-item label="频道代码">
		      <el-input v-model="editForm.code" auto-complete="off"></el-input>
		    </el-form-item>
		    <el-form-item label="频道名">
		      <el-input v-model="editForm.name" auto-complete="off"></el-input>
		    </el-form-item>
		  </el-form>
		  <span slot="footer" class="dialog-footer">
		    <el-button @click="dialogVisible = false">取 消</el-button>
		    <el-button type="primary" @click="editYes">确 定</el-button>
		  </span>
		</el-dialog>

		<!--底部-工具条-->
		<el-col :span="24" class="toolbar">
			<el-pagination  @current-change="handleCurrentChange"  :page-size="pageSize"  layout="prev, pager, next, jumper" :total="total" class="right">
		    </el-pagination>
		</el-col>
	</div>
</template>

<script>
	import axios from 'axios'
	
	
	import { addList,postList,removeList ,changeList} from '../../api/information';
	
	export default {
		data() {
			return {
				pageNum:1, // 当前页
				pageSize:20,  //  一页显示多少
				total:50,  //总条数
				form: {
					name:''
				},
				users: [],
				listLoading: false,
				sels: [], //列表选中列
				dialogVisible:false,   // 修改框设置
				editForm:{
					code:'',
					name:''
				},
				dialogDisplay:false,  // 新增框设置
				addForm:{
					code:'',
					name:''
				},
				changeId:''
			}
		},
		methods: {
			handleCurrentChange(val) {
				this.pageNum = val;
				this.getUsers();
			},
			// 查询
			seachHandle(){
				this.getUsers();
			},
			//获取用户列表
			getUsers() {
				let para = {
					"channelMName":this.form.name,  // 搜索条件
					"pageInfo":{
						"pageNum":this.pageNum,
						"pageSize":this.pageSize
					}
				};
				this.listLoading = true;
				postList(para).then((res) => {
					this.listLoading = false;
					this.users=res.datas;
					this.total=res.total; //  总条数
				});
			},
			//新增
			handleAdd:function(){
				this.addForm.code='';
				this.addForm.name='';
				this.dialogDisplay=true;
			},
			//新增-确认按钮
			addYes:function(){
				//参数
				let para = {
					"channelMCode":this.addForm.code,
					"channelMName":this.addForm.name,
				};
				addList(para).then((res)=>{
					this.getUsers();
					this.dialogDisplay=false;
				})
			},
			// 编辑
			handleEdit:function($index,rowData){
				this.dialogVisible=true;
				this.editForm.name=rowData.channelMName;
				this.editForm.code=rowData.channelMCode;
				this.changeId=$index;
			},
			// 编辑-确认按钮
			editYes:function(){
				let para ={
					"uuid":this.changeId,
					"channelMName":this.editForm.name,
					"channelMCode":this.editForm.code
				}
				changeList(para).then((res) => {
					if(res.status===0){
						this.getUsers();
						this.dialogVisible=false;
						this.$message({
				            type: 'success',
				            message: '修改成功!'
				        });
					}else{
						this.$message({
				            type: 'info',
				            message: '修改失败'
				        });
					}
				});
			},
			// 删除
			handleDel:function($index,rowData){
				//提示用户 是否真的要删除这条消息
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		       }).then(() => {
		        	let para={
		        		uuid : $index
		        	}
		        	removeList(para).then((res) => {
						if(res.status===0){
							this.$message({
					            type: 'success',
					            message: '删除成功!'
					        });
					        this.getUsers();
						}else{
							this.$message({
					            type: 'info',
					            message: '删除失败'
					        });
						}
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