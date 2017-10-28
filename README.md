ListView
========

A simple ListView for Android

Install
-------

```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

```groovy
implementation 'com.github.jtn-d:listview:0.1.0'
```

Usage
-----
```kotlin
class UserListView : ListView<User> {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onBindItem(holder: ListViewHolder<User>?, position: Int) {
        val view = holder?.itemView!!
        val item = holder.item!!

        view.full_name?.text = item.name.full
        view.email?.text = item.email
    }
}
```

```xml
<UserListView
    app:items="@{vm.items}"
    app:endOfList="@{vm.endOfList}"
    app:listitem="@layout/user_listitem"
    app:moreitem="@layout/more_listitem"
    app:onLoadMoreListener="@{vm::onLoadMore}"
    app:onSelectedListener="@{vm::onSelected}">
</UserListView>
```

```kotlin
class UsersViewModel : ViewModel(), ListView.OnSelectedListener,
        ListView.OnLoadMoreListener {

    var items: ObservableArrayList<User> = ObservableArrayList<User>()
    var error: ObservableField<String> = ObservableField()
    var loading: ObservableBoolean = ObservableBoolean(false)
    var endOfList: ObservableBoolean = ObservableBoolean(false)

    init {
        getUsers(0, 20)
    }

    override fun onLoadMore(page: Int, itemCount: Int) {
        getUsers(page, 20)
    }

    override fun onSelected(index: Int) {
    }

    fun getUsers(page: Int, results: Int) {
        ...
        items.addAll(users)
    }
}
```