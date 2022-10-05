import 'dart:convert';
/// id : 28
/// mobile : "18180319876"
/// password : "velit non adipisicing"
/// nickname : "陆芳"
/// roles : "elit non et cillum amet"
/// avatar : "http://dummyimage.com/100x100"
/// createTime : "1993-09-21 12:50:49"
/// updateTime : "2008-06-01 19:12:02"
/// bonus : 20

User userFromJson(String str) => User.fromJson(json.decode(str));
String userToJson(User data) => json.encode(data.toJson());
class User {
  User({
      num? id, 
      String? mobile, 
      String? password, 
      String? nickname, 
      String? roles, 
      String? avatar, 
      String? createTime, 
      String? updateTime, 
      num? bonus,}){
    _id = id;
    _mobile = mobile;
    _password = password;
    _nickname = nickname;
    _roles = roles;
    _avatar = avatar;
    _createTime = createTime;
    _updateTime = updateTime;
    _bonus = bonus;
}

  User.fromJson(dynamic json) {
    _id = json['id'];
    _mobile = json['mobile'];
    _password = json['password'];
    _nickname = json['nickname'];
    _roles = json['roles'];
    _avatar = json['avatar'];
    _createTime = json['createTime'];
    _updateTime = json['updateTime'];
    _bonus = json['bonus'];
  }
  num? _id;
  String? _mobile;
  String? _password;
  String? _nickname;
  String? _roles;
  String? _avatar;
  String? _createTime;
  String? _updateTime;
  num? _bonus;
User copyWith({  num? id,
  String? mobile,
  String? password,
  String? nickname,
  String? roles,
  String? avatar,
  String? createTime,
  String? updateTime,
  num? bonus,
}) => User(  id: id ?? _id,
  mobile: mobile ?? _mobile,
  password: password ?? _password,
  nickname: nickname ?? _nickname,
  roles: roles ?? _roles,
  avatar: avatar ?? _avatar,
  createTime: createTime ?? _createTime,
  updateTime: updateTime ?? _updateTime,
  bonus: bonus ?? _bonus,
);
  num? get id => _id;
  String? get mobile => _mobile;
  String? get password => _password;
  String? get nickname => _nickname;
  String? get roles => _roles;
  String? get avatar => _avatar;
  String? get createTime => _createTime;
  String? get updateTime => _updateTime;
  num? get bonus => _bonus;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = _id;
    map['mobile'] = _mobile;
    map['password'] = _password;
    map['nickname'] = _nickname;
    map['roles'] = _roles;
    map['avatar'] = _avatar;
    map['createTime'] = _createTime;
    map['updateTime'] = _updateTime;
    map['bonus'] = _bonus;
    return map;
  }

  @override
  String toString() {
    return 'User{_id: $_id, _mobile: $_mobile, _password: $_password, _nickname: $_nickname, _roles: $_roles, _avatar: $_avatar, _createTime: $_createTime, _updateTime: $_updateTime, _bonus: $_bonus}';
  }
}