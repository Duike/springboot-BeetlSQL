sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,name,age,username,role_id,create_date

updateSample
===
	
	id=#id#,name=#name#,age=#age#,username=#username#,role_id=#roleId#,create_date=#createDate#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(age)){
	 and age=#age#
	@}
	@if(!isEmpty(username)){
	 and username=#username#
	@}
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	
	