import pygetwindow as gw


class WindowsManager:
    def __init__(self):
        self.windows = None
        self.selected_window = None

    def find_windows(self, window_name):
        del self.windows
        self.windows = gw.getWindowsWithTitle(window_name)

    def get_windows_name(self):
        if self.windows is None:
            raise Exception("First find the windows!")
        else:
            return list(map(lambda win: win.title, self.windows))

    def select_window(self, i: int):
        if self.windows is None:
            raise Exception("First find the windows!")
        elif len(self.windows) == 0:
            raise Exception(f"None windows of were found!")
        elif i >= len(self.windows) or i < 0:
            raise Exception("Wrong choice of windows")
        else:
            self.selected_window = self.windows[i]

    def get_selected_window_name(self):
        return self.selected_window.title

    def get_selected_window_topleft(self):
        if self.selected_window is None:
            return (0,0)
        return self.selected_window.topleft

    def focus_selected_window(self):
        self.selected_window.restore()
        self.selected_window.activate()


