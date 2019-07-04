sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,name,age,username,password,role_id,create_date,is_active

updateSample
===
	
	id=#id#,name=#name#,age=#age#,username=#username#,password=#password#,role_id=#roleId#,create_date=#createDate#,is_active=#isActive#

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
	@if(!isEmpty(password)){
	 and password=#password#
	@}
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	@if(!isEmpty(isActive)){
	 and is_active=#isActive#
	@}
	
	