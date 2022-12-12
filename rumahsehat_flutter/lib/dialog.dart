import 'package:flutter/material.dart';

class AlertDialogNew extends StatelessWidget {
  final String title;
  final String content;
  final List<Widget> actions;

  const AlertDialogNew({
    required this.title,
    required this.content,
    this.actions = const [],
  });

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(
        title,
      ),
      actions: actions,
      content: Text(
        content,
      ),
    );
  }
}
