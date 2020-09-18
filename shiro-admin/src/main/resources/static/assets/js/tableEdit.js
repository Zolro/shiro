var TABLE_HTML = {
  top_tableEditHtml: "",
  bottom_tableEditHtml: "",
  top_add_tableEditHtml: "",
  bottom_add_tableEditHtml: ""
}


TABLE_HTML.top_tableEditHtml =
    `<form class="layui-form" id="popForm" lay-filter="popFromId">
  <div class="layui-form-item" style="display:none;">
    <label class="layui-form-label">ID</label>
    <div class="layui-input-block">
      <input type="text" name="id" autocomplete="off" disable class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">档号</label>
    <div class="layui-input-block">
      <input type="text" name="fileNumber" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗名称</label>
    <div class="layui-input-block">
      <input type="text" name="fondName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">文件标题</label>
    <div class="layui-input-block">
      <input type="text" name="fileTitle" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类名称</label>
    <div class="layui-input-block">
      <input type="text" name="categoryName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">档案类别</label>
    <div class="layui-input-block">
      <input type="text" name="fileCategory" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限代码</label>
    <div class="layui-input-block">
      <input type="text" name="storagePeriodCode" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密级</label>
    <div class="layui-input-block">
      <input type="text" name="secretLevel" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">组织机构</label>
    <div class="layui-input-block">
      <input type="text" name="organization" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <input type="text" name="remarks" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">存放位置</label>
    <div class="layui-input-block">
      <input type="text" name="storageLocation" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">登记号</label>
    <div class="layui-input-block">
      <input type="text" name="licenseNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">载体数量</label>
    <div class="layui-input-block">
      <input type="text" name="carriersNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗号</label>
    <div class="layui-input-block">
      <input type="text" name="fondNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">案卷号</label>
    <div class="layui-input-block">
      <input type="text" name="filesNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">盒号</label>
    <div class="layui-input-block">
      <input type="text" name="boxNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">附件</label>
    <div class="layui-input-block">
      <input type="text" name="annex" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">问题</label>
    <div class="layui-input-block">
      <input type="text" name="problem" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限</label>
    <div class="layui-input-block">
      <input type="text" name="storageTime" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类号</label>
    <div class="layui-input-block">
      <input type="text" name="categoryNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="top_test_edit__form">立即提交</button>
      <button type="button" id="top_test_edit__form_init" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
  </form>
  `;


TABLE_HTML.bottom_tableEditHtml =
    `<form class="layui-form" id="popForm-2" lay-filter="popFromId-2">
  <div class="layui-form-item" style="display:none;">
    <label class="layui-form-label">ID</label>
    <div class="layui-input-block">
      <input type="text" name="id" autocomplete="off" disable class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">文件级档号</label>
    <div class="layui-input-block">
      <input type="text" name="levelNumber" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类名称</label>
    <div class="layui-input-block">
      <input type="text" name="categoryName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">档案类别</label>
    <div class="layui-input-block">
      <input type="text" name="fileCategory" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗名称</label>
    <div class="layui-input-block">
      <input type="text" name="fondName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限代码</label>
    <div class="layui-input-block">
      <input type="text" name="storagePeriodCode" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密级</label>
    <div class="layui-input-block">
      <input type="text" name="secretLevel" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">文件标题</label>
    <div class="layui-input-block">
      <input type="text" name="fileTitle" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">组织机构</label>
    <div class="layui-input-block">
      <input type="text" name="organization" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <input type="text" name="remarks" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗号</label>
    <div class="layui-input-block">
      <input type="text" name="fondNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">案卷号</label>
    <div class="layui-input-block">
      <input type="text" name="filesNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">责任者</label>
    <div class="layui-input-block">
      <input type="text" name="person" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">附件</label>
    <div class="layui-input-block">
      <input type="text" name="annex" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">张页号</label>
    <div class="layui-input-block">
      <input type="text" name="pageNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">问题</label>
    <div class="layui-input-block">
      <input type="text" name="problem" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限</label>
    <div class="layui-input-block">
      <input type="text" name="storageTime" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">附注</label>
    <div class="layui-input-block">
      <input type="text" name="note" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">卷内顺序号</label>
    <div class="layui-input-block">
      <input type="text" name="volumeNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类号</label>
    <div class="layui-input-block">
      <input type="text" name="categoryNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">形成日期</label>
    <div class="layui-input-block">
      <input type="text" name="writingDate" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="bottom_test_edit__form">立即提交</button>
      <button type="button" id="bottom_test_edit__form_init" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
  </form>
  `
;


TABLE_HTML.top_add_tableEditHtml =
    `<form class="layui-form" id="popForm-3" lay-filter="popFromId-3">
  <div class="layui-form-item">
    <label class="layui-form-label">档号</label>
    <div class="layui-input-block">
      <input type="text" name="fileNumber" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗名称</label>
    <div class="layui-input-block">
      <input type="text" name="fondName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">文件标题</label>
    <div class="layui-input-block">
      <input type="text" name="fileTitle" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类名称</label>
    <div class="layui-input-block">
      <input type="text" name="categoryName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">档案类别</label>
    <div class="layui-input-block">
      <input type="text" name="fileCategory" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限代码</label>
    <div class="layui-input-block">
      <input type="text" name="storagePeriodCode" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密级</label>
    <div class="layui-input-block">
      <input type="text" name="secretLevel" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">组织机构</label>
    <div class="layui-input-block">
      <input type="text" name="organization" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <input type="text" name="remarks" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">存放位置</label>
    <div class="layui-input-block">
      <input type="text" name="storageLocation" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">登记号</label>
    <div class="layui-input-block">
      <input type="text" name="licenseNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">载体数量</label>
    <div class="layui-input-block">
      <input type="text" name="carriersNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗号</label>
    <div class="layui-input-block">
      <input type="text" name="fondNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">案卷号</label>
    <div class="layui-input-block">
      <input type="text" name="filesNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">盒号</label>
    <div class="layui-input-block">
      <input type="text" name="boxNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">附件</label>
    <div class="layui-input-block">
      <input type="text" name="annex" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">问题</label>
    <div class="layui-input-block">
      <input type="text" name="problem" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限</label>
    <div class="layui-input-block">
      <input type="text" name="storageTime" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类号</label>
    <div class="layui-input-block">
      <input type="text" name="categoryNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="top_add_test_edit__form">立即提交</button>
      <button type="button" id="top_add_test_edit__form_init" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
  </form>
  `;


TABLE_HTML.bottom_add_tableEditHtml =
    `<form class="layui-form" id="popForm-4" lay-filter="popFromId-4">
  <div class="layui-form-item">
    <label class="layui-form-label">文件级档号</label>
    <div class="layui-input-block">
      <input type="text" name="levelNumber" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类名称</label>
    <div class="layui-input-block">
      <input type="text" name="categoryName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">档案类别</label>
    <div class="layui-input-block">
      <input type="text" name="fileCategory" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗名称</label>
    <div class="layui-input-block">
      <input type="text" name="fondName" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限代码</label>
    <div class="layui-input-block">
      <input type="text" name="storagePeriodCode" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密级</label>
    <div class="layui-input-block">
      <input type="text" name="secretLevel" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">文件标题</label>
    <div class="layui-input-block">
      <input type="text" name="fileTitle" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">组织机构</label>
    <div class="layui-input-block">
      <input type="text" name="organization" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <input type="text" name="remarks" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">全宗号</label>
    <div class="layui-input-block">
      <input type="text" name="fondNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">案卷号</label>
    <div class="layui-input-block">
      <input type="text" name="filesNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">责任者</label>
    <div class="layui-input-block">
      <input type="text" name="person" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">附件</label>
    <div class="layui-input-block">
      <input type="text" name="annex" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">张页号</label>
    <div class="layui-input-block">
      <input type="text" name="pageNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">问题</label>
    <div class="layui-input-block">
      <input type="text" name="problem" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">保管期限</label>
    <div class="layui-input-block">
      <input type="text" name="storageTime" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">附注</label>
    <div class="layui-input-block">
      <input type="text" name="note" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">卷内顺序号</label>
    <div class="layui-input-block">
      <input type="text" name="volumeNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分类号</label>
    <div class="layui-input-block">
      <input type="text" name="categoryNumber" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">形成日期</label>
    <div class="layui-input-block">
      <input type="text" name="writingDate" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="bottom_add_test_edit__form">立即提交</button>
      <button type="button" id="bottom_add_test_edit__form_init" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
  </form>
  `;