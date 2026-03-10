import "@testing-library/jest-native/extend-expect";
import { Alert } from "react-native";

jest.spyOn(Alert, 'alert').mockImplementation(
  (title: string, message?: string, buttons?: any[]) => {
    if (buttons) {
      const confirmButton = buttons.find((b: any) => b.text === 'Có');
      if (confirmButton?.onPress) {
        confirmButton.onPress();
      }
    }
  }
);