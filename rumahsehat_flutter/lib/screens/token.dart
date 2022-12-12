import 'package:flutter_secure_storage/flutter_secure_storage.dart';

final storage = new FlutterSecureStorage();

Future<String> getToken() async {
  String? value = await storage.read(key: "token");
  return value.toString();
}

Future<String> getId() async {
  String? value = await storage.read(key: "id");
  return value.toString();
}

Future<String> getUsername() async {
  String? value = await storage.read(key: "username");
  return value.toString();
}
